package simpledb.file;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import simpledb.server.SimpleDB;

public class FileTest {
   public static void main(String[] args) throws IOException {
      SimpleDB db = new SimpleDB("filetest", 400, 8);
      FileMgr fm = db.fileMgr();
      BlockId blk = new BlockId("testfile", 2);
      int pos1 = 88;

      Page p1 = new Page(fm.blockSize());
      p1.setString(pos1, "abcdefghijklm");
      int size = Page.maxLength("abcdefghijklm".length());
      int pos2 = pos1 + size;
      p1.setInt(pos2, 345);
      fm.write(blk, p1);

      Page p2 = new Page(fm.blockSize());
      fm.read(blk, p2);
      System.out.println("offset " + pos2 + " contains " + p2.getInt(pos2));
      System.out.println("offset " + pos1 + " contains " + p1.getString(pos1));

      Page p3 = new Page(fm.blockSize());
      p3.setShort(0, (short)123);
      p3.setShort(2, (short)456);
      System.out.println("offset 0 shortint " + p3.getShort(0));
      System.out.println("offset 2 shortint " + p3.getShort(2));


      Page p4 = new Page(fm.blockSize());
      p3.setBoolean(0, true);
      p3.setBoolean(1, false);
      System.out.println("offset 0 boolean " + p3.getBoolean(0));
      System.out.println("offset 1 boolean " + p3.getBoolean(1));
      
      Page p5 = new Page(fm.blockSize());
      try {
         p5.setDate(0, new SimpleDateFormat("yyyy/MM/dd").parse("1970/1/1"));         
      } catch (ParseException e) {
         e.printStackTrace();
      }
      System.out.println("offset 0 Date = " + p5.getDate(0));
   }
}
