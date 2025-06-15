# 🧾 `Environment Variables on Workflow, Step, and Job Level`

---

### ✅ **Trigger**

```yaml
on:
  workflow_dispatch:
```

This means the workflow must be **manually triggered** via the **GitHub Actions UI**.

---

### 🧪 **Workflow-Level Environment Variable**

```yaml
env:
  WF_LEVEL_ENV: Available to all jobs
```

This defines an environment variable named `WF_LEVEL_ENV` that is **accessible in all jobs** of this workflow unless overridden.

---

### 🧱 **Job: `env-vars-and-context`**

```yaml
if: github.ref == 'refs/heads/master'
```

> 💡 **Note**: This job only runs if the workflow is triggered on the `main` branch. Otherwise, it will be skipped.

```yaml
env:
  JOB_LEVEL_ENV: Available to all steps in env-vars-and-context job
```

This sets `JOB_LEVEL_ENV` that will be available **to all steps inside this job**.

---

### 🧾 **Steps**

#### 🔹 Step 1 - Log `GITHUB_REF` Environment Variable

```yaml
echo $GITHUB_REF
```

This logs the raw environment variable `GITHUB_REF`, which is typically something like:

```
refs/heads/master
```

---

#### 🔹 Step 2 - Log GitHub Context Value

```yaml
echo '${{ github.ref }}'
```

This uses the **GitHub context expression** to print the current branch reference. Same result as `$GITHUB_REF`, but this is using **expression syntax**.

---

#### 🔹 Step 3 - Log Custom Environment Variables

```yaml
env:
  STEP_LEVEL_ENV: Available to only this step
  WF_LEVEL_ENV: Overridden in this step
```

> 💡 Here you're adding a **step-level env variable** (`STEP_LEVEL_ENV`) and **overriding** the `WF_LEVEL_ENV` from the workflow level.

```bash
echo "🔹 STEP_LEVEL_ENV (step-only): $STEP_LEVEL_ENV"
echo "🔹 WF_LEVEL_ENV (workflow-level, overridden in step): $WF_LEVEL_ENV"
echo "🔹 JOB_LEVEL_ENV (job-level): $JOB_LEVEL_ENV"
```

#### 🔍 Output Explanation:

You will see:

```
🔹 STEP_LEVEL_ENV (step-only): Available to only this step
🔹 WF_LEVEL_ENV (workflow-level, overridden in step): Overridden in this step
🔹 JOB_LEVEL_ENV (job-level): Available to all steps in env-vars-and-context job
```

---

### 📚 Summary of Variable Scopes

| **Scope**      | **Key**             | **Visible To**                                   |
| -------------- | ------------------- | ------------------------------------------------ |
| Workflow-level | `WF_LEVEL_ENV`      | All jobs/steps (unless overridden)               |
| Job-level      | `JOB_LEVEL_ENV`     | All steps within the job                         |
| Step-level     | `STEP_LEVEL_ENV`    | Only the step it's defined in                    |
| GitHub Context | `${{ github.ref }}` | Accesses metadata about the branch, commit, etc. |

---

Would you like a visual diagram to explain scopes?
