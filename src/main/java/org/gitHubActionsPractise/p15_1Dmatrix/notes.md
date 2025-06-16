# ✅ Workflow Trigger

```yaml
on:
  workflow_dispatch:
```

This allows you to **manually trigger** the workflow from the GitHub Actions UI.

---

## 🧪 Job: `test-execution-via-1D-matrix`

### ➤ `strategy.matrix`

```yaml
strategy:
  matrix:
    test-suite-name: [smokeTestSuite.xml, regressionTestSuite.xml, sanityTestSuite.xml]
```

This creates **3 parallel jobs** (1 job per test suite):

* Job 1 runs with `test-suite-name=smokeTestSuite.xml`
* Job 2 runs with `test-suite-name=regressionTestSuite.xml`
* Job 3 runs with `test-suite-name=sanityTestSuite.xml`

Each job executes the **same steps**, but with a different value for `${{ matrix.test-suite-name }}`.

---

## 🔄 Steps Breakdown

### 🔹 Step 1 – Log Start

```yaml
- name: step 1. Starting the test
  run: echo 'Testing is getting started'
```

Just prints a message indicating test execution is starting.

---

### 🔹 Step 2 – Checkout Code

```yaml
- uses: actions/checkout@v4
```

This checks out the code from your repository so Maven can access the `*.xml` suite files and the source code.

---

### 🔹 Step 3 – Setup Java and Maven

```yaml
- uses: actions/setup-java@v3
  with:
    distribution: 'temurin'
    java-version: '17'
```

Installs Java 17 (Temurin distribution), typically required for Maven/Java projects.

---

### 🔹 Step 4 – Run Test Suite

```yaml
- run: |
    echo "Executing '${{ matrix.test-suite-name }}' tests..."
    mvn clean test -Dsuite=${{ matrix.test-suite-name }}
```

* Prints which suite is being executed.
* Then runs Maven tests using a custom property `suite`.
* You likely use this `suite` property in your `testng.xml` or Java config like:

```java
String suiteName = System.getProperty("suite");
// Then load the respective TestNG suite file dynamically
```

---

### 🧠 Summary

| Concept             | Purpose                                              |
| ------------------- | ---------------------------------------------------- |
| Matrix strategy     | To run same job multiple times with different inputs |
| `${{ matrix.xxx }}` | Access current matrix value                          |
| `-Dsuite=...`       | Pass custom property to Maven                        |
| Parallelism         | Speeds up testing by running suites simultaneously   |

---

Let me know if you want to:

* Convert this to a 2D matrix (e.g., test suite + environment),
* Dynamically generate matrix from file/array,
* Or integrate with TestNG programmatically.
