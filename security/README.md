This security module can be used to add security to the theatre.

For this, you need to add the following dependency to your theatre project:

    <dependency>
      <groupId>org.dotwebstack.theatre</groupId>
      <artifactId>dotwebstack-theatre-security</artifactId>
      <version>0.0.1</version>
    </dependency>

Your application.yml should contain the registration of users, roles and (encoded) user passwords, like:

	security:
	  authentication: form
	  users:
	    - user: user
	      password: $2a$11$Z0uBLK6ocuMoqVYFUimZre./Y4iBq5v8IrhOVpR.oVJzJK6V7.lVu
	      role: USER
	    - user: user2
	      password: $2a$11$XmoBho2sMxvNfm7ogh/OdOX7EUF..s7fN96hVqqCGVCtE60xPzyI.
	      role: different
	  protect:
	    - path: /demo/editor
	      role: USER

Passwords are encoded using bcrypt. The `pwencrypt` folder contains a java program to create encoded versions of a password.