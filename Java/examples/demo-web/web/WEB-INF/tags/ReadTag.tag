<%@ tag import="java.io.File" %>
<%@ tag import="java.io.FileInputStream" %>
<%@ tag import="java.io.IOException" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="fileDir" required="true" %>
<%@ attribute name="fileName" required="true" %>
<%
    File dir=new File(fileDir);
    File f=new File(dir,fileName);
    try {
        FileInputStream in = new FileInputStream(f);
        int m = -1;
        byte[] bb = new byte[1024];
        String content = null;
        while ((m = in.read(bb)) != -1) {
            content = new String(bb, 0, m);
            out.print(content);
        }
    }catch (IOException e){
        out.print("文件读取失败"+e);
    }
%>
