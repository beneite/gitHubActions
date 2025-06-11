# ✅ **Workflow Name**

```yaml
name: this Workflow will trigger when PR merged on branch-Production on opened, synchronize, reopened, closed actions.
```

This is the **name** of your workflow and appears in the **Actions tab** on GitHub. It clearly states when and on which branch the workflow triggers.

---

## 🧲 **Trigger Block**

```yaml
on:
  pull_request:
    types: [opened, synchronize, reopened, closed]
    branches:
      - productionBranch
```

This workflow will trigger when a **pull request** targeting the branch `productionBranch` is:

| Action        | What it means                                    |
| ------------- | ------------------------------------------------ |
| `opened`      | A new PR is created targeting `productionBranch` |
| `synchronize` | New commits are pushed to the PR                 |
| `reopened`    | A previously closed PR is reopened               |
| `closed`      | The PR is closed (either merged or discarded)    |

---

## ⚙️ **Job: `run-smoke-test`**

```yaml
jobs:
  run-smoke-test:
    runs-on: ubuntu-latest
    if: github.event.pull_request.merged == true || github.event.action != 'closed'
```

* This job will **only run if**:

    * The PR was **merged** (i.e. `merged == true`)
    * **OR** if the PR is still open (`action != 'closed'`)
* This ensures the job is **skipped for PRs that are closed without merging**.

---

## 🔽 **Steps Explained**

### 1. **Checkout the Code**

```yaml
- name: Checking out the repo...
  uses: actions/checkout@v4
```

This checks out your repository code to the runner so it can run tests on the latest PR code.

---

### 2. **Environment Setup**

```yaml
- name: Setting up the Test Environment
  run: echo "Ready to Start Smoke test"
```

Just prints a message. In real use, this could prepare environment variables, files, or configs.

---

### 3. **Run Smoke Test with TestNG**

```yaml
- name: Run TestNG tests
  run: mvn clean test -Dtest=SmokeTestClass.java
```

This command:

* Runs Maven
* Cleans any previously compiled files
* Runs tests **only in `SmokeTestClass`**

```bash
-Dtest=SmokeTestClass
```

You should update that line to:

```yaml
run: mvn clean test -Dtest=SmokeTestClass
```

---

### 4. **Teardown (Clean up / Final Step)**

```yaml
- name: Tearing down the Test Environment
  run: echo "Start Smoke test completed"
```

This just prints a message. Could be used to remove temp files, log cleanup, etc.

---

## ✅ Summary

| Aspect                                                      | Behavior           |
| ----------------------------------------------------------- | ------------------ |
| **Triggered on PR to `productionBranch`**                   | Yes                |
| **Runs for both PR creation/updates and merges**            | Yes                |
| **Skips on PRs that are closed without merging**            | Yes                |
| **Runs only specific TestNG test class (`SmokeTestClass`)** | Yes                |

---
