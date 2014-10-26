<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-2">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please Login</h3>
				</div>
				<div class="panel-body">
					<form role="form" action="j_spring_security_check" method="POST"
						accept-charset="UTF-8">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="E-mail"
									name="j_username" type="email" autofocus>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password"
									name="j_password" type="password" value="">
							</div>
							<div class="checkbox">
								<label> <input name="remember" type="checkbox"
									value="Remember Me" name="_spring_security_remember_me">
									Remember Me
								</label>
							</div>
							<button type="submit" class="btn btn-block btn-success">
								Login</button>
							<a href="registration" class="btn btn-block btn-primary">
								Registration </a>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
