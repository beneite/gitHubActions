# 🧾 Workflow Name

```yaml
name: Environment and repo level variables and secrets
```

This is the display name shown in GitHub Actions UI.

---

### 🚀 Trigger

```yaml
on:
  workflow_dispatch:
```

This allows you (or any user with write access) to **manually trigger** the workflow from the GitHub UI.

---

### ✅ Workflow Summary

You have **3 jobs**, each tied to a specific environment: `qa`, `dev`, `prod`. Each job:

* Runs on `ubuntu-latest`.
* Is associated with a GitHub **environment** (which can have secrets and protection rules).
* Pulls **secrets** and **variables** from:

    * Environment-specific secrets (like `DATABASE_CONNECTION_STRING`)
    * Repository-level variables (`WEB_URL`, `USER_NAME`, etc.)
* Prints them in the log (except `secrets`, which are masked as `***`).

---

### 🧱 Environment-Specific Jobs

#### 1. `job-for-qa-env`

```yaml
environment: "qa"
```

This job:

* Uses the `qa` environment.
* Loads environment/repo-level secrets and vars into environment variables.
* Prints those values using `echo`.

The same pattern is repeated for:

#### 2. `job-for-dev-env`

```yaml
environment: "dev"
```

#### 3. `job-for-prod-env`

```yaml
environment: "prod"
```

---

### 🧠 Understanding the Variables

```yaml
env:
  DATABASE_CONNECTION_STRING_SECRET: ${{ secrets.DATABASE_CONNECTION_STRING }}
  WEB_URL_ENV_VARIABLE: ${{ vars.WEB_URL }}
  USER_NAME_ENV_VARIABLE: ${{ vars.USER_NAME }}
  REPO_FIRST_VARIABLE_KEY_VARIABLE: ${{ vars.REPO_FIRST_VARIABLE_KEY }}
```

| Variable Name                       | Source                                 | Notes                  |
| ----------------------------------- | -------------------------------------- | ---------------------- |
| `DATABASE_CONNECTION_STRING_SECRET` | Environment **secret**                 | Masked in logs (`***`) |
| `WEB_URL_ENV_VARIABLE`              | Repository or environment **variable** | Visible in logs        |
| `USER_NAME_ENV_VARIABLE`            | Repository or environment **variable** | Visible in logs        |
| `REPO_FIRST_VARIABLE_KEY_VARIABLE`  | Repository **variable**                | Visible in logs        |

---

### 🔒 Why is the connection string masked?

GitHub Actions automatically **redacts secrets** (like `secrets.DATABASE_CONNECTION_STRING`) in logs for security reasons. Even if you `echo` them, you'll see `***`.

---

### ✅ Advantages of Your Setup

* 🧩 **Modularity**: Separate jobs for each environment.
* 🔐 **Security**: Uses GitHub's secrets mechanism.
* 📦 **Reusability**: Repo/environment variables can be reused across workflows.
* 📣 **Clear Logging**: Echoes make it easy to verify variable values (except secrets).

---