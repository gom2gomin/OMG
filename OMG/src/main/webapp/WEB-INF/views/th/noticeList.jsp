<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
<%@ include file="/WEB-INF/views/common/menu.jsp" %>
	<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">고객지원/</span> 공지사항</h4>
		<div class="table-responsive">
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>Project</th>
		        <th>Client</th>
		        <th>Users</th>
		        <th>Status</th>
		        <th>Actions</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> <span class="fw-medium">Angular Project</span></td>
		        <td>Albert Cook</td>
		        <td>
		          <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Lilian Fuller">
		              <img src="assets/img/avatars/5.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Sophia Wilkerson">
		              <img src="assets/img/avatars/6.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Christina Parker">
		              <img src="assets/img/avatars/7.png" alt="Avatar" class="rounded-circle">
		            </li>
		          </ul>
		        </td>
		        <td><span class="badge bg-label-primary me-1">Active</span></td>
		        <td>
		          <div class="dropdown">
		            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown"><i class="bx bx-dots-vertical-rounded"></i></button>
		            <div class="dropdown-menu">
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-edit-alt me-1"></i>Edit</a>
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-trash me-1"></i>Delete</a>
		            </div>
		          </div>
		        </td>
		      </tr>
		      <tr>
		        <td><i class="fab fa-react fa-lg text-info me-3"></i> <span class="fw-medium">React Project</span></td>
		        <td>Barry Hunter</td>
		        <td>
		          <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Lilian Fuller">
		              <img src="assets/img/avatars/5.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Sophia Wilkerson">
		              <img src="assets/img/avatars/6.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Christina Parker">
		              <img src="assets/img/avatars/7.png" alt="Avatar" class="rounded-circle">
		            </li>
		          </ul>
		        </td>
		        <td><span class="badge bg-label-success me-1">Completed</span></td>
		        <td>
		          <div class="dropdown">
		            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown"><i class="bx bx-dots-vertical-rounded"></i></button>
		            <div class="dropdown-menu">
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-edit-alt me-1"></i>Edit</a>
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-trash me-1"></i>Delete</a>
		            </div>
		          </div>
		        </td>
		      </tr>
		      <tr>
		        <td><i class="fab fa-vuejs fa-lg text-success me-3"></i> <span class="fw-medium">VueJs Project</span></td>
		        <td>Trevor Baker</td>
		        <td>
		          <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Lilian Fuller">
		              <img src="assets/img/avatars/5.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Sophia Wilkerson">
		              <img src="assets/img/avatars/6.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Christina Parker">
		              <img src="assets/img/avatars/7.png" alt="Avatar" class="rounded-circle">
		            </li>
		          </ul>
		        </td>
		        <td><span class="badge bg-label-info me-1">Scheduled</span></td>
		        <td>
		          <div class="dropdown">
		            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown"><i class="bx bx-dots-vertical-rounded"></i></button>
		            <div class="dropdown-menu">
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-edit-alt me-1"></i>Edit</a>
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-trash me-1"></i>Delete</a>
		            </div>
		          </div>
		        </td>
		      </tr>
		      <tr>
		        <td><i class="fab fa-bootstrap fa-lg text-primary me-3"></i> <span class="fw-medium">Bootstrap Project</span></td>
		        <td>Jerry Milton</td>
		        <td>
		          <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Lilian Fuller">
		              <img src="assets/img/avatars/5.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Sophia Wilkerson">
		              <img src="assets/img/avatars/6.png" alt="Avatar" class="rounded-circle">
		            </li>
		            <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top" class="avatar avatar-xs pull-up" title="Christina Parker">
		              <img src="assets/img/avatars/7.png" alt="Avatar" class="rounded-circle">
		            </li>
		          </ul>
		        </td>
		        <td><span class="badge bg-label-warning me-1">Pending</span></td>
		        <td>
		          <div class="dropdown">
		            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown"><i class="bx bx-dots-vertical-rounded"></i></button>
		            <div class="dropdown-menu">
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-edit-alt me-1"></i>Edit</a>
		              <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-trash me-1"></i>Delete</a>
		            </div>
		          </div>
		        </td>
		      </tr>
		    </tbody>
		  </table>
		</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>

</html>