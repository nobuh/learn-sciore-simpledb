package simpledb.file;

import java.nio.ByteBuffer;
import java.nio.charset.*;

public class Page {
   private ByteBuffer bb;
   public static Charset CHARSET = StandardCharsets.US_ASCII;

   // For creating data buffers
   public Page(int blocksize) {
      bb = ByteBuffer.allocateDirect(blocksize);
   }
   
   // For creating log pages
   public Page(byte[] b) {
      bb = ByteBuffer.wrap(b);
   }

   public int getShort(int offset) {
      return bb.getShort(offset);
   }

   public void setShort(int offset, short n) {
      bb.putShort(offset, n);
   }

   public int getInt(int offset) {
      return bb.getInt(offset);
   }

   // 取得済みのブロック bb に収まるかチェックしていないが
   // bb は blocksize の固定取得だけではなく log のときの参照割当のケースがあること
   // 取得していない領域への書き込みは java.nio.BufferOverflowException が出て
   // 保護されるので明示的なサイズの事前チェックが不要
   public void setInt(int offset, int n) {
      bb.putInt(offset, n);
   }

   public byte[] getBytes(int offset) {
      bb.position(offset);
      int length = bb.getInt();
      byte[] b = new byte[length];
      bb.get(b);
      return b;
   }

   // 取得済みのブロック bb に収まるかチェックしていないが
   // bb は blocksize の固定取得だけではなく log のときの参照割当のケースがあること
   // 取得していない領域への書き込みは java.nio.BufferOverflowException が出て
   // 保護されるので明示的なサイズの事前チェックが不要
   public void setBytes(int offset, byte[] b) {
      bb.position(offset);
      bb.putInt(b.length);
      bb.put(b);
   }
   
   public String getString(int offset) {
      byte[] b = getBytes(offset);
      return new String(b, CHARSET);
   }

   public void setString(int offset, String s) {
      byte[] b = s.getBytes(CHARSET);
      setBytes(offset, b);
   }

   public static int maxLength(int strlen) {
      float bytesPerChar = CHARSET.newEncoder().maxBytesPerChar();
      return Integer.BYTES + (strlen * (int)bytesPerChar);
   }

   // a package private method, needed by FileMgr
   ByteBuffer contents() {
      bb.position(0);
      return bb;
   }
}
