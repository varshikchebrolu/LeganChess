# Development Manual
### Dependencies
Our preferred Java build tool is Maven and we will use “npm” as our dependency manager for JavaScript. These two tools will take care of downloading and packaging all of our other dependencies. Linux or Mac based OS are the preferred development platforms, no Windows support is given at this time.
#### Java
Download and install the Java Development Kit(JDK). Ensure you install version >= 11.0
The JDK can be downloaded from directly from Oracle  [Here](https://www.oracle.com/java/technologies/downloads/)
The site contains instructions for installing JDK on all platforms. Ensure to including the installation location in your PATH environmental variable or create a JAVA_HOME environmental variable.  
(from cs314 documentation)
```
Once you've downloaded and unpacked your JDK (to a directory like jdk-11/), you'll want to add the following lines to your .bashrc (.bash_profile for Mac users):
export JAVA_HOME=/path/to/jdk
export PATH=$JAVA_HOME/bin:$PATH
Restart your terminal, and try to run java -version. If correctly installed, you should see the proper version coming from the JDK you chose.
```
#### Maven
Install through the package manager.  
On Linux:
```
sudo apt-get install maven
```
On Mac:
```
brew install maven
```
Ensure Java (JDK) is installed and running prior to this step and that the Environmental variables are set so that Maven knows where to look for the libraries.  
type "mvn --version" to verify installation.
#### NPM
It is preferred that you use NVM (Node Version Manager) is install NPM. Instructions for installing NVM can be found [here](https://github.com/nvm-sh/nvm) .The simplest way to install is to use the wget or curl commands
```
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
or
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
```
Installing with NVM:
Once NVM has been installed (check with nvm --version), you can install the latest LTS release of NodeJS with the following:
```
nvm install --lts
```
Now you can check To See If NodeJS and NPM where installed
```
node -v
npm -v
```

Note: Working versions for npm and node are as follows:
```
nvm -v : 0.39.0
node -v : v14.18.1
npm -v : 8.1.0
```

# IntelliJ (section taken from cs314 documentation)

[IntelliJ](https://www.jetbrains.com/idea/) is the IDE our company will be using. It has better support for the tools 
we will be using than Eclipse.

### Creating an Account

As students, you can get a license for the ultimate edition of IntelliJ for free. You'll need a free JetBrains Student 
Account. You can sign up for one [here](https://www.jetbrains.com/student/). In addition, when signing up for a 
JetBrains Student Account, you will notice the ability to use your GitHub PRO account to validate with JetBrains. If
you dont have a GitHub PRO account, it is free to use as a student, and at this point it would be a great opportunity to
get one (it is free!).

### IntelliJ installation

There are two versions of IntelliJ, the Community Edition and the Ultimate Edition. Either should work for the scope of 
this project, but the Ultimate Edition integrates better with JavaScript code. As mentioned above, students
have free access to the Ultimate Edition and other Jetbrains products. Either edition can be 
downloaded [from the Jetbrains website](https://www.jetbrains.com/idea/download).

### Launching IntelliJ

IntelliJ is available on the lab machines under the command:

```
/usr/local/idea/bin/idea.sh
```

If you don't want to remember this, you can add an alias to `~/.bashrc`:

```bash
alias intellij="/usr/local/idea/bin/idea.sh"
```

Restart your terminal, and now you can simply type `intellij` to launch.

### Opening the Project

When first opening IntelliJ, a window should open inviting you to open a
project. Select `Check out from version control` and continue to select "GitHub"
from the dropdown.

Refer to our [GitHub guide](https://github.com/csucs314s20/guide/tree/master/guides/git/IntelliJ.md)
for more information on how to clone your project from IntelliJ.

Because we are using Maven, IntelliJ should automatically import all of the
necessary dependencies for the project. This sometimes takes a while, so be
patient. If you need to set your project JDK, refer to the documentation
[here](https://www.jetbrains.com/help/idea/configuring-build-jdk.html).

### Building in IntelliJ

For the most part, the run script should be comprehensive enough to build your
project. [Here](https://www.jetbrains.com/help/idea/working-with-tool-windows.html#tool_window_quick_access)
are some visual instructions on opening a terminal in IntelliJ. In addition, several configuration builds have been 
included with the source files provided to you. Try running each of them and take note of what each one does.

Here we describe how to run the code, specifically using the [bin/run.sh script](./bin/run.sh).

## Run Configurations

We have two configurations for our code. Here is a brief description of each and when you should use them.

### Development

#### Dev Environment Description

The development environment is the default environment for you to use while
developing your code. First of all, every time you change a file and save it,
the browser that you're viewing the project in will automatically refresh with
the updated code. This allows you to make frequent changes and quickly see them
in your dev environment. Unfortunately, since Java code is compiled (whereas Javascript
is interpreted), we cannot do the same process for the server. You will still need
to restart the server for every change you make.

Additionally, for the development server, the code is delivered "as is" to the client. While
this may seem like it should be the default, it usually isn't, for reasons we will describe below.
Since the code reaches your browser without being changed, you can view it in the browser, which
can help with debugging.

#### Using the Dev Environment

If you don't set the environment variable `CS314_RUN_MODE`, then the run script will default to development mode. To run the 
server in development mode, invoke the run script as is:

```bash
./bin/run.sh
```

This starts two processes:
* the client code running via `npm run devClient` listening on port 3000
* the server code running via `npm run server` listening on port 8000 

Your default browser should open automatically and display the project's
homepage after a few seconds (notice that it is running on `localhost:3000`).
If you see an error banner displayed at the top, it is likely that your JAR is
running on an unexpected port, or is not running at all.

The server listening on port 3000 is the "hot server" which tells the browser
when the code has been changed so it can refresh and get the new code. It
is different from your server which handles API requests from your client,
which listens on port 8000.

### Production

#### Prod Environment Description

While the dev environment is useful for developers, there are a lot of differences between
the dev and prod environments because user demand is different from dev demand.

First, in the prod environment, the client code is "minified". Imagine how big the Javascript
code for a website like Google Maps or Youtube might be. A user with low-throughput internet
might struggle downloading all the code. As a result, we use Webpack to "minify" our code. It
essentially reduces the code to be as small as possible without changing it's functionality.
This is great for users with low bandwith. A side-effect of minification is heavy obfuscation
of the code. This might be good for security purposes, but terrible for developers who might
want to look at the code in the browser while it's running, or get the line number from a
`console.log` statement.

Additionally, the prod environment "packages" resources together. For every image on the
website, this must be downloaded, and an image can be even worse for low-throughput users
than large code. As a result, Webpack will discard any resource which it believes isn't
necessary for running the client. This results in some side-effects. For example, if I am
trying to display an image:

```js
# Method 1
<Img src="./image.jpg />

# Method 2
import image_src from "./image.jpg";
<Img src={image_src} />
```

These normally would do the same thing. However, in the first example, Webpack doesn't
recognize that the image file is a required resource. This means the image won't be
properly packaged, and although it might appear in the dev environment, any user will
be unable to see this image. In the second, Webpack notices the import and does recognize 
the image as a required resource. As a result, you should *always* run your code in prod
once you believe everything is working to ensure that the change in environment leaves
functionality consistent.

#### Using the Prod Environment

The easiest way to run the server and make sure everything works is to use the
run script with the flag to tell which environment to use:

```bash
./bin/run.sh -e prod
```

This will install all npm dependencies (if they haven't been already), bundle
together all of the Javascript source, compile and test your React and Java Code, package
everything into a single JAR, and start running the server on the default port. Visit `http://localhost:8000` 
to see the web page.

Investigate what the run scripts actually do to better understand how our system
is built. All of the commands they run can be run manually as well, to perform a
single step of the build operation only.

### Deployment

Ultimately, you will deploy your production server to a different machine. To
package everything into a single executable jar file to be submitted through
checkin, use the run.sh script with the `-d` flag set:

```bash
./bin/run.sh -d
```

This will create a directory called `target` if it does not already exist and
write the jar file to this directory. This jar can then be copied and ran on 
any other machine with java.

### IntelliJ Run Configurations

In addition to the bash scripts mentioned above, we have provided a set of IntelliJ run configurations for you in
your repository. These can be invoked via the green run button towards the top left of the screen.

## Database Setup Considerations (excerpts taken from cs314 documentation)


You will have two environments that you will have to worry about to begin with:
1) On the CSU Network (This should be the default since you cannot change the behavior when
deployed to black-bottle)
2) Off the CSU Network

#### On campus network:
The URL for the database when running on a CS machine is:
```
jdbc:mariadb://faure.cs.colostate.edu/cs414_team4
```

#### Off capmus network:
The URL for the database when tunneling is:  
```
jdbc:mariadb://127.0.0.1:56247/cs414_team4
```
To Connect to the database through our code, on our personal machines:  
You will have to forward your connection to a CS machine first. We can use an SSH tunnel to achieve this:  
```
ssh -L <some-port>:faure.cs.colostate.edu:3306 <eid>@<cs-machine>  
```

To distinguish between environments the implemented solution is as follows:  
Set a Bash shell variable to indicate that the code is running on your personal computer.    
```
export CS414_USE_DATABASE_TUNNEL=true  
```
You should put this line of code into your ~/.bashrc or ~/.bash_profile so it remains set instead of having to set it every time you run the code.  

### Environment-Specific Details
Do not put this shell variable in your .bashrc if you are developing on any of these environments:  

VSCode RemoteSSH: You are connecting to the lab machines through SSH and running your code on those lab machines.  
Therefore, your code is running within the CSU network and inside the firewall, so it can access Faure directly.  

VSCode Docker: You will need to use the connectdb command in a separate terminal within the container, which is  
described in more detail in the Chapter 1 Docker with VSCode section. This command will run "ssh -L 3306:faure.cs.colostate.edu:3306 eid@machine.cs.colostate.edu". It
will also treat 127.0.0.1 as faure.cs.colostate.edu, so it will require the Faure link
"jdbc:mariadb://faure.cs.colostate.edu/cs414_team4".

In-person Lab Machine use: Similar to RemoteSSH, you can access Faure directly and do not
need port forwarding or the shell variable.

You will need this shell variable defined if you are developing on any of these environments:  

Local Linux or Mac: You are not on the campus network (or not inside the CSU firewall), so you
will need to add the export line shown above to your .bashrc or .bash_profile (Mac). This will
allow you to tunnel through a lab machine to reach inside the firewall and access Faure.
Tunneling requires you to port forward through a lab machine to Faure in a separate terminal.  

You can add an entry to your .ssh/config like this (change myeid and use a different machine to
prevent overload):
```
Host db414
Hostname jefferson-city.cs.colostate.edu  
user myeid
LocalForward 56247 faure:3306
```
and run "ssh db414" in a separate terminal. This LocalForward line is also included in the cs314
host config which you can copy from the CS314 Website -> DevOps -> Forwarding page.
Alternatively, a command like "ssh -L 56247:faure.cs.colostate.edu:3306 eid@machine.cs.colostate.edu" can work too.



