# 📄 **Workflow Overview**

## **Workflow Name**:
`a simple workflow to find java, maven, docker version on runner machine`

**Trigger**:
Runs every time you **push** to the repository.

```yaml
on: [ push ]
```

---

### 💡 High-Level Concept

This workflow runs **3 separate jobs**, each on its own GitHub-hosted Ubuntu machine:

* ✅ **Job 1** → Checks Java version
* ✅ **Job 2** → Checks Maven version
* ✅ **Job 3** → Checks Docker version

These jobs **run in parallel** by default because there are no dependencies defined between them.

![Screenshot 2025-06-10 at 10.03.28 PM.png](Screenshot%202025-06-10%20at%2010.03.28%E2%80%AFPM.png)

---

### ⚙️ Breakdown of Each Job

---

#### 🔹 `java-version-check-job-1`

```yaml
runs-on: ubuntu-latest
```

→ This job runs on a fresh Ubuntu 22.04 VM.

**Steps**:

1. Print start time.
2. Run `java --version` to check Java version.
3. Print end time.

---

#### 🔹 `maven-version-check-job-2`

Same structure as above, but it runs:

```bash
mvn --version
```

→ Displays Maven version pre-installed on the runner.

---

#### 🔹 `docker-version-check-job-3`

Again, same structure but runs:

```bash
docker --version
```

→ Verifies the Docker CLI version available on the runner.

---

### 🧠 What This Teaches You

| Concept             | What You’re Learning                           |
| ------------------- | ---------------------------------------------- |
| Multiple jobs       | Defining and running jobs independently        |
| Job parallelism     | Jobs run in parallel unless you define `needs` |
| Runners             | Each job runs on a clean VM (`ubuntu-latest`)  |
| Step sequencing     | Each job’s steps run in sequence               |
| Logging with `date` | Time tracking in jobs                          |

---

### ✅ Output in GitHub Actions UI

In the GitHub Actions tab, you’ll see **three separate job cards**, each labeled:

* `java-version-check-job-1`
* `maven-version-check-job-2`
* `docker-version-check-job-3`

Each job shows the console output for each step — ideal for debugging or learning.

---