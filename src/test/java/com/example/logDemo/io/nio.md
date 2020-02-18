###Invariants: mark <= position <= limit <= capacity

###flip
    buf.put(magic);    // Prepend header, position=1
    in.read(buf);      // Read data into rest of buffer，比如从in读入ABCD，pos=6
    buf.flip();        // pos=0, limit=6
    out.write(buf);    // Write header + data to channel, 把0到6之间的数据写入out, post=6

###rewind
    out.write(buf);    // Write remaining data，如：把buffer里的数据ABCD写入out, pos=4, limit=4
    buf.rewind();      // pos=0, limit=4
    buf.get(array);    // Copy data into array，把0到4之间的数据写入array，pos=4

###clear
    buf.clear();     // Prepare buffer for reading; position = 0; limit = capacity; 清空整个buffer        
    in.read(buf);    // Read data 

###mark
    mark=position 打标记

###reset
    int m = mark;
    position = m; 当前位置回退到标记位


###ByteBuffer.compact
    Once you are done reading data out of the Buffer you have to make the Buffer ready for writing again. 
    You can do so either by calling clear() or by calling compact().
    If there is still unread data in the Buffer, and you want to read it later, 
    but you need to do some writing first, call compact() instead of clear().

    buf.clear();     // Prepare buffer for use
    while (in.read(buf) >= 0 || buf.position != 0) {
      buf.flip();
      out.write(buf);
      buf.compact();    // In case of partial write
    }

