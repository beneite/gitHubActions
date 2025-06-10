# If you want to **skip running a GitHub Actions workflow when pushing code**, there are a few effective and commonly used methods.

---

## ✅ 1. **Use `[skip ci]` or `[ci skip]` in Commit Message**

This is the **most common and easy** way to skip workflow execution on push.

### ➤ How to use it:

When you commit, include `[skip ci]` or `[ci skip]` anywhere in your commit message:

```bash
git commit -m "Refactored README [skip ci]"
git push
```

### 🧠 What happens:

GitHub detects this keyword and **skips all workflows triggered by `push` or `pull_request` events**.

> ✅ This works globally across all workflows in the repo!

---

## ✅ 2. **Use `if:` Conditional in Workflow**

If you want to **skip only certain jobs**, but still allow the workflow to trigger, use this:

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    if: "!contains(github.event.head_commit.message, '[skip-job]')"
    steps:
      - run: echo "Running build job"
```

### ➤ How to use it:

Use `[skip-job]` (or any keyword you define) in your commit message:

```bash
git commit -m "Minor fix [skip-job]"
git push
```

### 🧠 This skips the **job**, not the whole workflow.

---

## ✅ 3. **Add Path Filters to Workflow**

If you want to run a workflow **only when certain files or folders are changed**, use `paths:`:

```yaml
on:
  push:
    paths:
      - 'src/**'
      - '!docs/**'
```

### ➤ What it does:

This will run the workflow only when files in `src/` are changed, and skip if only `docs/` or other paths change.

---

## ✅ 4. **Manual Workaround with Environment Variable (Advanced)**

Set a custom environment variable to control execution, like:

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    if: env.SKIP_WORKFLOW != 'true'
    steps:
      - run: echo "Running job"
```

Then, trigger the workflow manually or via an API with `SKIP_WORKFLOW=true`.

---

## ✅ Quick Summary

| Method                   | Skips                                |
| ------------------------ | ------------------------------------ |
| `[skip ci]` in commit    | ✅ Entire workflow                    |
| `if:` with message check | ✅ Specific job(s)                    |
| `paths:` filters         | ✅ Workflow based on file changes     |
| Env var flags            | ✅ Workflow or job with control logic |

---
 
Let me know which approach you want to implement and I can tailor an example!
