# Status Check

This GitHub Actions workflow demonstrates **status check functions** in action — specifically `success()`, `failure()`,
`cancelled()`, `always()`, and how `needs` affects downstream jobs.

Let’s break down each job and the concepts it showcases:

---

### ✅ **`success-job`**

Runs unconditionally (manually triggered via `workflow_dispatch`), and all steps succeed.

#### Steps:

1. **Sleep 5 seconds** – Just a delay.
2. **Check for success()** – Always `true` since the previous step succeeded.
3. **Always runs** – `always()` ensures the step runs regardless of job or step status.

---

### ❌ **`failure-job`**

This job simulates a failure.

#### Steps:

1. **Sleep 20 seconds** – Simulate work.
2. **Force fail with `exit 1`** – This causes the job to fail.
3. **Conditional on failure()** – This will run because step 2 failed.

    * Additionally uses: `steps.quitingStep2.conclusion == 'failure'` to confirm it was this step that failed.
4. **Always runs** – `always()` ensures the step runs despite the failure.

---

### ⛔ **`cancelled-job`**

Simulates cancellation — to test this, **you must manually cancel the workflow run from the UI** during its sleep
period.

#### Steps:

1. **Sleep for a long time** (2000s = \~33 minutes) — gives you time to cancel.
2. **Runs only if job is cancelled** – Will only run if the workflow was manually cancelled while sleeping.

> Note: If not cancelled manually, step 2 won’t execute since `cancelled()` will be `false`.

---

### 🚫 **`skipped-job`**

This job is **dependent** on `failure-job`, using `needs`.

```yaml
needs: failure-job
if: success()
```

* Since `failure-job` fails (`exit 1`), this condition:

  ```yaml
  if: success()
  ```

  evaluates to **`false`**, so **`skipped-job`** will be skipped entirely.

* Its single step echoes a message, but it won't run — the whole job gets skipped.

---

### 🧠 Summary of Key Concepts

| Status Function         | Runs When…                                                                                                                  |
|-------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| `success()`             | Previous steps/job succeed                                                                                                  |
| `failure()`             | Any prior step fails                                                                                                        |
| `cancelled()`           | Job is manually cancelled                                                                                                   |
| `always()`              | Always runs, no matter what                                                                                                 |
| `needs` + `if`          | Used to **conditionally run jobs based on dependencies' outcomes**                                                          |
| continue-on-error: true | continue-on-error: true is a directive in GitHub Actions that tells the workflow not to fail the job even if the step fails |
