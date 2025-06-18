This GitHub Actions workflow named **"Artifact for test run"** is designed to:

1. **Run Maven test cases** based on the user's selected suite (`smoke`, `regression`, `sanity`).
2. **Upload the test results** as an artifact.
3. **Download the test results** in a second job and display their contents in the logs.

---

### 🔁 Trigger

```yaml
on:
  workflow_dispatch:
    inputs:
      testType:
```

* Allows you to trigger the workflow manually from the GitHub UI.
* Asks for the `testType` input (choice between `smoke`, `regression`, or `sanity`).

---

### 🧪 Job 1: `run-test-cases-job`

Runs the selected test suite and uploads the results.

#### Steps:

1. **Checkout Code**
   Checks out your repository so it can run Maven commands on the code.

2. **List files before running tests**
   Helps debug to confirm the code is pulled.

3. **Run Selected Tests**
   Based on the `testType` input, it runs one of the test suites:

    * `mvn clean test -Dsuite=smokeTestSuite.xml`
    * etc.

4. **List files after tests**
   Verifies if the test results were generated (usually in `target/surefire-reports`).

5. **Upload Test Reports**
   Uploads the `target/surefire-reports/` directory as an artifact named `test-reports-upload-name`.

---

### 📥 Job 2: `display-test-report-job`

Downloads and displays the test result files from Job 1.

#### Key Points:

* **Depends on Job 1** via `needs: run-test-cases-job`.

#### Steps:

1. **List files before downloading**
   Should be mostly empty (initial state of the runner).

2. **Download artifact**
   Downloads the `test-reports-upload-name` artifact into `downloaded-reports/`.

3. **List all files after download**
   Uses `find .` to print the directory tree so you can verify files are downloaded.

4. **Display test results**
   Uses `cat downloaded-reports/*.txt` to print the `.txt` test result files (usually these are like `TEST-*.txt` or `*.txt` in `surefire-reports`).

---

### ✅ Use Case

Perfect for debugging CI pipelines or showing test results directly in GitHub UI — especially when you're running different test suites or environments and want to **store and inspect artifacts** later.

Let me know if you want to also **convert XML to HTML** or integrate with test reporting tools.
