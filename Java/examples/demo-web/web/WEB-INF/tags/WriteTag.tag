<%@ tag import="java.io.File" %>
<%@ tag import="java.io.FileOutputStream" %>
<%@ tag import="java.io.IOException" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="fileContent" required="true" %>
<%@ attribute name="fileName" required="true" %>
<%@ attribute name="fileDir" required="true" %>
<%
    File dir=new File(fileDir);
    if(!dir.exists()){
        dir.mkdir();
    }
    File f=new File(dir,fileName);
    try {
        FileOutputStream output=new FileOutputStream(f);
        byte[] bb=fileContent.getBytes();
        output.write(bb,0,bb.length);
        output.close();
        out.print("文件写入成功");
        out.print("<br>文件所在目录:"+fileDir);
        out.print("<br>文件的名字:"+fileName);
    } catch (IOException e) {
        out.print("文件写入失败"+e);
    }
%>
