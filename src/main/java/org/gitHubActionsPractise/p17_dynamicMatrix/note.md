# ✅ Full Workflow Breakdown

### 🔹 1. Workflow Name and Trigger

```yaml
name: Dynamic matrix demonstration - running different test suites on different runner machine os types

on:
  workflow_dispatch:
    inputs:
      os-type:
        description: Enter the github-runner machine names comma separated
        type: string
        default: "ubuntu-latest,macos-latest"
      test-suite-name:
        description: Enter the full testNg suite
        type: string
        default: "smokeTestSuite.xml,regressionTestSuite.xml,sanityTestSuite.xml"
```

* This is a **manually triggered workflow** using the **`workflow_dispatch`** event.
* The user provides two **comma-separated strings** as inputs:

    * `os-type` → e.g. `ubuntu-latest,macos-latest`
    * `test-suite-name` → e.g. `smokeTestSuite.xml,regressionTestSuite.xml,...`

---

### 🔹 2. `prepare-matrix-job`: Convert inputs into a usable matrix

```yaml
jobs:
  prepare-matrix-job:
    runs-on: ubuntu-latest
    outputs:
      matrix-to-feed-to-jobs: ${{ steps.matrix-preparation-step-id.outputs.result }}
```

This job prepares the matrix (from user inputs) for the next job to consume.

#### ➤ Step: Parse inputs into JSON arrays

```yaml
    steps:
      - name: Converting the input comma separated string to json array with keys as os-array & test-suite-array
        uses: actions/github-script@v6
        id: matrix-preparation-step-id
        with:
          script: |
            return {
              osArray: context.payload.inputs['os-type'].split(','),
              testSuiteArray: context.payload.inputs['test-suite-name'].split(',')
            }
          result-encoded: json
```

* This script **splits** the comma-separated strings into arrays:

    * `"ubuntu-latest,macos-latest"` → `["ubuntu-latest", "macos-latest"]`
    * `"smokeTestSuite.xml,regressionTestSuite.xml"` → `["smokeTestSuite.xml", "regressionTestSuite.xml"]`
* It returns a JSON object like:

```json
{
  "osArray": ["ubuntu-latest", "macos-latest"],
  "testSuiteArray": ["smokeTestSuite.xml", "regressionTestSuite.xml"]
}
```

#### ➤ Optional Debug Step

```yaml
      - name: printing the json obtained below
        run: echo '${{ steps.matrix-preparation-step-id.outputs.result }}'
```

* Just prints the generated JSON matrix in the logs (for debugging or verification).

---

### 🔹 3. `test-execution-via-dynamic-matrix-job`: Use the dynamic matrix

```yaml
  test-execution-via-dynamic-matrix-job:
    needs: prepare-matrix-job
```

This job waits for the matrix-preparation job to finish.

#### ➤ Define the matrix from the previous job’s output

```yaml
    strategy:
      matrix:
        os: ${{ fromJson(needs.prepare-matrix-job.outputs.matrix-to-feed-to-jobs).osArray }}
        test-suite-name: ${{ fromJson(needs.prepare-matrix-job.outputs.matrix-to-feed-to-jobs).testSuiteArray }}
```

* It dynamically parses the JSON returned from the previous job and assigns:

    * `os` = list of OS types
    * `test-suite-name` = list of test suite files

**Total job runs** = number of OSes × number of test suites
Example: 2 OSes × 3 suites = **6 matrix jobs**.

#### ➤ Runs the job on the OS from the matrix

```yaml
    runs-on: ${{ matrix.os }}
```

---

### 🔹 4. Steps for each matrix job

Each matrix combination (e.g. `ubuntu-latest` + `smokeTestSuite.xml`) will execute these steps:

#### Step 1: Log the start

```yaml
      - name: step 1. Starting the test
        run: echo 'Testing is getting started'
```

#### Step 2: Checkout the repository code

```yaml
      - name: Step 2 - Checkout Code
        uses: actions/checkout@v4
```

#### Step 3: Setup Java and Maven

```yaml
      - name: Step 3 - Setup Java and Maven
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'
```

#### Step 4: Run the test suite

```yaml
      - name: Step 4 - Run the selected tests
        run: |
          echo "Executing '${{ matrix.test-suite-name }}' tests..."
          mvn clean test -Dsuite=${{ matrix.test-suite-name }}
```

This executes the Maven command with the appropriate test suite from the matrix.

---
