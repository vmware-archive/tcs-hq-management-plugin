<%--
  ~ Copyright (C) 2009-2015  Pivotal Software, Inc
  ~
  ~ This program is is free software; you can redistribute it and/or modify
  ~ it under the terms version 2 of the GNU General Public License as
  ~ published by the Free Software Foundation.
  ~
  ~ This program is distributed in the hope that it will be useful,
  ~ but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~ GNU General Public License for more details.
  ~
  ~ You should have received a copy of the GNU General Public License
  ~ along with this program; if not, write to the Free Software
  ~ Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
  --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<ul>
  <li>
    <spring:url value="/app/{settingsId}/services/{serviceId}/connectors/" var="url">
      <spring:param name="settingsId" value="${settings.humanId}" />
      <spring:param name="serviceId" value="${service.humanId}" />
    </spring:url>
    <a href="${fn:escapeXml(url)}">Connectors</a>
  </li>
  <li>
    <spring:url value="/app/{settingsId}/services/{serviceId}/engine/" var="url">
      <spring:param name="settingsId" value="${settings.humanId}" />
      <spring:param name="serviceId" value="${service.humanId}" />
    </spring:url>
    <a href="${fn:escapeXml(url)}">Engine</a>
  </li>
  <li>
    <spring:url value="/app/{settingsId}/services/{serviceId}/engine/hosts/" var="url">
      <spring:param name="settingsId" value="${settings.humanId}" />
      <spring:param name="serviceId" value="${service.humanId}" />
    </spring:url>
    <a href="${fn:escapeXml(url)}">Hosts</a>
  </li>
</ul>
