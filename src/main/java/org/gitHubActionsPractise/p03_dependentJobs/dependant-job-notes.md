# 📄 **Workflow Name**

```yaml
name: a simple workflow to find java, maven, docker version on runner machine using dependant task
```

This is a **linear/serial execution workflow**, where each job waits for the previous one to finish.

---

### ⚙️ **Workflow Trigger**

```yaml
on: [push]
```

This workflow will run **every time you push** changes to any branch of your repo.

---

### 🔗 **Job Dependencies Explained**

Here's a **visual representation** of your job dependencies:

```
initial-setup-job
       ↓
java-version-check-job-1
       ↓
maven-version-check-job-2
       ↓
docker-version-check-job-3
```

Each job only starts after the **previous job completes successfully**.

---
## 🧪 Output in GitHub UI
![Screenshot 2025-06-10 at 10.21.13 PM.png](Screenshot%202025-06-10%20at%2010.21.13%E2%80%AFPM.png)

---

## 🔍 **Detailed Breakdown of Each Job**


#### 🛠️ **Job 1: `initial-setup-job`**

```yaml
steps:
  - Print start time
  - Echo "Workflow started"
  - Print end time
```

* A basic "setup" job to initialize the workflow.
* Acts as the starting point for all other jobs.

---

#### ☕ **Job 2: `java-version-check-job-1`**

```yaml
needs: initial-setup-job
```

* **Waits for `initial-setup-job` to finish** before it starts.
* Runs `java --version` to show the Java version.
* Sleeps for **10 seconds** to simulate a longer task.

---

#### 📦 **Job 3: `maven-version-check-job-2`**

```yaml
needs: java-version-check-job-1
```

* **Waits for `java-version-check-job-1` to complete**.
* Checks the Maven version using `mvn --version`.
* Sleeps for **20 seconds** to simulate a longer task.

---

#### 🐳 **Job 4: `docker-version-check-job-3`**

```yaml
needs: maven-version-check-job-2
```

* **Runs last**, after Maven check.
* Shows Docker version using `docker --version`.
* Sleeps for **10 seconds**.

---
