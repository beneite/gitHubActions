# ✅ **Workflow Name**

```yaml
name: this Workflow will trigger when PR merged on branch-productionBranch on opened, synchronize, reopened, closed actions.
```

This is a **human-readable title** that shows in the **GitHub Actions UI**.

---

## 🧲 **Triggers (`on:` block)**

```yaml
on:
  push:
    branches:
      - productionBranch
  pull_request:
    types: [opened, synchronize, reopened, closed]
    branches:
      - productionBranch
```

This defines when the workflow will be triggered:

| Event          | Condition                                                                                 | Meaning                                                                                   |
| -------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `push`         | to `productionBranch`                                                                     | Triggered when someone pushes directly (e.g., merge, commit) to `productionBranch`        |
| `pull_request` | PR targets `productionBranch` and the PR is: `opened`, `updated`, `reopened`, or `closed` | Triggered when a PR is created, updated, reopened, or closed targeting `productionBranch` |

---

## 🚦 **Job: `run-smoke-test`**

```yaml
jobs:
  run-smoke-test:
    runs-on: ubuntu-latest
```

* This job runs on the latest **Ubuntu runner** provided by GitHub.

---

### ✅ Conditional Job Execution

```yaml
    if: github.event_name == 'push' || github.event.pull_request.merged == true || github.event.action != 'closed'
```

This ensures the job runs **only when appropriate**:

| Condition                                  | Meaning                                                         |
| ------------------------------------------ | --------------------------------------------------------------- |
| `github.event_name == 'push'`              | If it's a push to `productionBranch`, run the job               |
| `github.event.pull_request.merged == true` | If the PR was merged (not just closed), run the job             |
| `github.event.action != 'closed'`          | If the PR is being created or updated (not closed), run the job |

✅ This way, you **avoid running the job on closed-but-not-merged PRs**, which are often discarded PRs.

---

### 🔧 **Job Steps**

#### Step 1: Checkout Repository

```yaml
- name: Checking out the repo...
  uses: actions/checkout@v4
```

This pulls your repo code into the runner, so you can run commands like `mvn test`.

---

#### Step 2: Setup

```yaml
- name: Setting up the Test Environment
  run: echo "Ready to Start Smoke test"
```

This is just a message, but this step can be expanded later to include:

* environment setup
* test data preparation
* setting environment variables

---

#### Step 3: Run Smoke Tests

```yaml
- name: Running Smoke tests for build stability
  run: mvn clean test -Dtest=SmokeTestClass
```

This:

* Runs a **specific TestNG test class** named `SmokeTestClass`
* Ensures only that class’s `@Test` methods are executed
* Uses `mvn clean` to delete previous builds

🔸 Tip: Make sure `SmokeTestClass` is in the correct Maven test directory structure (`src/test/java/...`).

---

#### Step 4: Teardown

```yaml
- name: Tearing down the Test Environment
  run: echo "Start Smoke test completed"
```