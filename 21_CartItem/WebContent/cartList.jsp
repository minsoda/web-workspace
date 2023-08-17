<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <style>
      td {
        width: 100px;
        height: 200px;
      }
    </style>
  </head>
  <body>
    <h1>장바구니</h1>
    <a href="itemList.do">쇼핑 계속 하기</a>
    <table border="1">
      <tr>
        <th>번호</th>
        <th>상품이미지</th>
        <th>상품명</th>
        <th>상품가격</th>
        <th>수량</th>
        <th><button>삭제</button></th>
      </tr>
      <tr>
        <td>${item.itemId}</td>
        <td><img src="${data-id.pictureUrl}" /></td>
        <td>${data-id.itemName}</td>
        <td>${data-id.price}</td>
        <td></td>
        <td><input type="checkbox" /></td>
      </tr>
    </table>

    <script>
      function view() {
        localStorage.getItem($("data-id").prop());
       
      }

    //  function remove() {
      //  localStorage.removeItem("?????");
      //  location.reload();
    //  }
    </script>
  </body>
</html>
