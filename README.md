<div id="top"></div>
<!-- ABOUT THE PROJECT -->

### About The Project

This project is part of the QA Backed Test for Wallbox.

### Built With

The project has been built using: 
* [Java](https://www.java.com/)
* [TestNG](https://testng.org/doc/)
* [RestAssured](https://rest-assured.io/)
* [Extent Reports](https://www.extentreports.com/)


<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

To get this project running you only need to make sure you meet the following prerequisites:

### Prerequisites

Setup an environment (Windows/UNIX) with the following installed:
* Java 1.8 JDK
* [Maven](https://maven.apache.org/install.html)

### Installation

Using the project is very simple:

1. Clone the repo
   ```sh
   git clone https://github.com/nassimoufighou/wallbox_backend_qa_test.git
   ```
2. Install dependencies
   ```sh
   mvn clean install -DskipTests
   ```
3. Compile code
   ```sh
   mvn compile -DskipTests
   ```
As a note, the <code>-DskipTests</code> parameter is to prevent running the tests after performing any action. Above, we are updating, installing dependencies, and compiling the code without running any test.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage
All the available test are in the <code>testng.xml</code> file. As we are using the Maven Surfire plugin, the following command will run all the tests specified in the XML file. 
To run the test, you just need to be in the tests package and use: 
   ```js
   mvn test
   ```
By default, the report is called <code>report.html</code> and it's generated in:
   ```js
/europcar/reports/
   ```
You can configure the path were the report will be generated. To do so, call the method <code>TestSuiteConfig.setPathReport()</code> in the <code>BaseAPITest</code> class (don't forget to include the filename in the path!).

_For more information, please refer to the [Maven Surfire Plugin Documentation](https://maven.apache.org/surefire/maven-surefire-plugin/)_
<p align="right">(<a href="#top">back to top</a>)</p>

<!-- LICENSE -->
## Notes
Some [Github Actions](https://github.com/features/actions) have been setted up to ensure the good health of the project. You can find the corresponding <code>compile-install-tests.yml</code>
in the following path:
   ```js
/europcar/.github/workflows
   ```

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Nassim Oufighou - [LinkedIn](https://www.linkedin.com/in/nassim-oufighou/) - nassim.oufighou@gmail.com

Project Link: [https://github.com/nassimoufighou/wallbox_backend_qa_test](https://github.com/nassimoufighou/europcarmobility_api_qa_test)

<p align="right">(<a href="#top">back to top</a>)</p>
