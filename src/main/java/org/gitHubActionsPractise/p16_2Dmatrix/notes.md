You're now using a **2D matrix strategy** in your GitHub Actions workflow to **run each test suite on multiple operating systems**, which is useful for cross-platform testing.

---

## 🔁 Trigger

```yaml
on:
  workflow_dispatch:
```

Manual trigger from GitHub Actions UI.

---

## ⚙️ Job: `test-execution-via-1D-matrix` (Misnamed: It’s 2D now)

### 🔄 Matrix Definition

```yaml
strategy:
  matrix:
    os: [ ubuntu-latest, macos-latest ]
    test-suite-name: [smokeTestSuite.xml, regressionTestSuite.xml, sanityTestSuite.xml]
```

This creates a **2×3 matrix** = **6 total job combinations**, one for every combination of:

| OS              | Test Suite                |
| --------------- | ------------------------- |
| `ubuntu-latest` | `smokeTestSuite.xml`      |
| `ubuntu-latest` | `regressionTestSuite.xml` |
| `ubuntu-latest` | `sanityTestSuite.xml`     |
| `macos-latest`  | `smokeTestSuite.xml`      |
| `macos-latest`  | `regressionTestSuite.xml` |
| `macos-latest`  | `sanityTestSuite.xml`     |

Each of these will run in parallel.

---

## 🧩 Job Execution

```yaml
runs-on: ${{ matrix.os }}
```

Each job runs on the OS from the current matrix combination (`ubuntu-latest` or `macos-latest`).

---

## 🔄 Steps in Each Job

### 1️⃣ Print Start Message

```yaml
echo 'Testing is getting started'
```

### 2️⃣ Checkout Repo

```yaml
uses: actions/checkout@v4
```

### 3️⃣ Set Up Java 17

```yaml
uses: actions/setup-java@v3
with:
  distribution: 'temurin'
  java-version: '17'
```

### 4️⃣ Run Test

```bash
echo "Executing '${{ matrix.test-suite-name }}' tests..."
mvn clean test -Dsuite=${{ matrix.test-suite-name }}
```

You're running Maven with a custom `-Dsuite` property. In your Java code or TestNG XML config, you likely read this to select the correct suite to run.

---

## ✅ Benefits of This Setup

| Feature                     | Why it's useful                            |
| --------------------------- | ------------------------------------------ |
| **2D matrix**               | Combines OS + test suite for full coverage |
| **Cross-platform testing**  | Ensures your code works on Linux & macOS   |
| **Parallel execution**      | Faster feedback by testing in parallel     |
| **Dynamic suite selection** | Cleaner and reusable workflow code         |

---
