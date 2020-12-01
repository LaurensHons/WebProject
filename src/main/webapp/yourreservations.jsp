<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="domain.model.Person" %>
<%@ page import="domain.model.ContactTracingService" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Your Reservation</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
    <body>
        <div id="container">
            <header>
                <h1><span>XXX</span></h1>
                <jsp:include page="nav.jsp">
                    <jsp:param name="page" value="profile"/>
                </jsp:include>

                <c:if test="${not empty errors}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach items="${errors}" var="error">
                                <li>${errors}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <c:if test="${not empty messages}">
                    <div class="message">
                        <ul>
                            <c:forEach items="${messages}" var="message">
                                <li>${message}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <h2>Your Contacts</h2>
            </header>
            <main>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Test Result</th>
                    </tr>
                    <c:forEach var="reservation" items="${reservations}">
                        <tr>
                            <td>${reservation.getDateString()}</td>
                            <td>${reservation.getUserid()}</td>
                            <td>${reservation.getEmail()}</td>
                            <td>${reservation.getPhonenr()}</td>
                        </tr>
                    </c:forEach>

                </table>
            </main>
            <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
        </div>
    </body>
</html>