A **scheduled workflow** in GitHub Actions is used to run jobs automatically at defined time intervals — much like a cron job.

---

### ✅ Basic Example of a Scheduled Workflow

```yaml
name: Daily Test Run

on:
  schedule:
    - cron: '0 3 * * *'  # Runs daily at 3:00 AM UTC

jobs:
  scheduled-tests:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Code
        uses: actions/checkout@v4

      - name: Set up Java
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'

      - name: Run Tests
        run: mvn clean test -Dsuite=regressionTestSuite.xml
```

---

### 🕒 Cron Syntax Guide

GitHub uses [standard cron syntax](https://en.wikipedia.org/wiki/Cron), and **all times are in UTC**.

```
# ┌───────────── minute (0 - 59)
# │ ┌───────────── hour (0 - 23)
# │ │ ┌───────────── day of month (1 - 31)
# │ │ │ ┌───────────── month (1 - 12)
# │ │ │ │ ┌───────────── day of week (0 - 6) (Sunday = 0)
# │ │ │ │ │
# │ │ │ │ │
# * * * * *
```

### 📌 Examples

| Schedule Description  | Cron Expression |
| --------------------- | --------------- |
| Every day at midnight | `0 0 * * *`     |
| Every Monday at 7 AM  | `0 7 * * 1`     |
| Every 2 hours         | `0 */2 * * *`   |
| Every 15 minutes      | `*/15 * * * *`  |

---

### ✅ Best Practices

1. **Don't forget to push the workflow to your default branch** (e.g., `main` or `master`).
2. GitHub runs scheduled workflows only if:

    * The repo has recent activity (within 60 days).
    * The workflow exists on the **default branch**.

---