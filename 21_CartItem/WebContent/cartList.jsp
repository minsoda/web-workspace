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
      <thead>
        <tr>
          <th>번호</th>
          <th>상품이미지</th>
          <th>상품명</th>
          <th>상품가격</th>
          <th>수량</th>
          <th><button onclick="deleteStorage()">삭제</button></th>
        </tr>
      </thead>

      <tbody></tbody>

      <tfoot>
        <tr>
          <!-- 계속 변경되니까 span id로 넣어둠 -->
          <td colspan="6">총 결제금액 :<span id="resultTotal"></span></td>
        </tr>
      </tfoot>
    </table>

    <script>
      // 들어오는 순간 호출!
      let amount = 1;
      let totalPrice = 0;
      refreshPage();
      //id는 고유의 값이라서 적용안되니까 class로 줌!
      $(".up").on("click", function () {
        // console.log($(this).next().html()); // div 로 접근 -> 수량에 접근
        $(this)
          .next()
          .html(eval($(this).next().html()) + 1);
        amount = $(this).next().html();
        // $(this).parent().prev().html(); // 현재위치에서 부모로 위로 -> 금액에 접근
        totalPrice += eval($(this).parent().prev().html());
        $("#resultTotal").html(totalPrice);
        // alert('up');
      });

      $(".down").on("click", function () {
        if (amount > 1) {
          // console.log($(this).prev());
          $(this)
            .prev()
            .html(eval($(this).prev().html()) - 1);
          amount = $(this).prev().html();
          totalPrice -= eval($(this).parent().prev().html());
          $("#resultTotal").html(totalPrice);
        }
        // alert('down');
      });

      function refreshPage() {
        let html = "";
        let count = 0;
        // 인덱스라서 in으로!
        for (let key in localStorage) {
          if (key === "length") break;
          console.log(key);
          const data = localStorage.getItem(key).split(",");
          console.log(data);
          html +=
            "<tr>" +
            "<td>" +
            ++count +
            "</td>" +
            "<td><img src=" +
            data[2] +
            " width=150 height=150></td>" +
            "<td>" +
            data[0] +
            "</td>" +
            "<td>" +
            data[1] +
            "</td>" +
            "<td>" +
            "<img src=img/up.jpg width=10 height=10 style=cursor:pointer; class=up>" +
            "<div>" +
            amount +
            "</div>" +
            "<img src=img/down.jpg width=10 height=10 style=cursor:pointer; class=down>" +
            "</td>" +
            "<td>" +
            "<input value=" +
            key +
            " type=checkbox class=deleteItem>" +
            "</td>" +
            "</tr>";
          // 문자라서 숫자로 변환 필용!
          totalPrice += eval(data[1]);
        }
        //tbody에 담는당
        $("tbody").append(html);
        $("#resultTotal").html(totalPrice);
      }

      // 삭제 기능
      function deleteStorage() {
        const check = document.querySelectorAll(".deleteItem");
        console.log(check);
        for (let i = 0; i < check.length; i++) {
          if (check[i].checked === true) {
            localStorage.removeItem(check[i].value);
            location.reload();
          }
        }
      }
    </script>
  </body>
</html>
