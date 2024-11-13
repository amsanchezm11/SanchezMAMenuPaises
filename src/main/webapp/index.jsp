<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="estilo" value="/CSS/estilo.css" scope="application" />
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />
<jsp:useBean id="listaOrdenada" class="es.albarregas.beans.ListaPaisesBean" scope="page" />
<fmt:setLocale value="es_ES" scope="application" />
<!DOCTYPE html>
<html lang="es">
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Elija un pa&iacute;s</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body>
        <c:import url="/INC/cabecera.inc"/>
        <div>
            <form action="${contexto}/FrontController" method="post" class="formulario">
                <div class="container-input">
                    <label>Elija un pa&iacute;s: </label>
                    <select name="pais">
                        <option disabled selected>Elige una opci&oacute;n</option>

                        <c:forEach var="elemento" items="${listaOrdenada.lista}">
                            <!-- Hago una variable con el valor del elemento en mayúsculas para compararlo con 
                                 la lista de los Enums -->
                            <c:set var="inicio" value="${fn:indexOf(elemento, '(')}" />
                            <c:set var="elementoSinParentesis" value="${fn:substring(elemento, 0, inicio)}" />
                            <c:set var="elementoUpper" value="${elementoSinParentesis.toUpperCase()}" />               
                            <c:choose>
                                <c:when test="${listaOrdenada.listaTraducida.contains(elementoUpper)}">
                                    <option class="negrita" value="${elemento}">${elemento}</option>
                                </c:when>
                                <c:when test="${elemento == 'Arabia Saudí(ar)'||elemento == 'Alemania(de)'||elemento == 'España(es)' ||elemento == 'Italia(it)' ||elemento == 'Madagascar(mg    )'}">
                                    <option class="negrita" value="${elemento}">${elemento}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${elemento}">${elemento}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>      
                </div>
                <div class="aviso">
                    <span>*Disponemos de traducci&oacute;n de los pa&iacute;ses resaltados en color</span>

                </div>
                <div class="container-boton">
                    <input type="submit" name="enviar" value="Enviar"/>
                </div>
            </form>                         
        </div>
        <c:import url="/INC/pie.inc"/>
    </body>
</html>