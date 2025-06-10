# 📄 **File Overview**

**Workflow Name**:
`a simple workflow to find java, maven, docker version on runner machine`

![Screenshot 2025-06-10 at 9.47.11 PM.png](Screenshot%202025-06-10%20at%209.47.11%E2%80%AFPM.png)

# **Trigger**:
This workflow runs **on every `push`** to the repository — that is, whenever you push new commits to any branch.

---

## ⚙️ **Job: `java-version-check-job`**

* **Runs On**: `ubuntu-latest`
  → This uses a fresh Ubuntu virtual machine (currently `ubuntu-22.04` as of 2025).

## 👇 This job includes the following steps:

---

### 🧩 Step-by-Step Breakdown

#### ✅ **Step 1: Starting the Job**

```yaml
- name: step 1. Starting the Job
  run: echo "Starting the Job at $(date)"
```

* Logs the start time of the job using the `date` command.

---

#### 🧪 **Step 2: Finding Java Version**

```yaml
- name: step 2. finding java version
  run: java --version
```

* Runs `java --version` to show the installed Java version.
* On `ubuntu-latest`, it typically prints something like:

  ```
  openjdk 17.0.9 2023-10-17
  ```

---

#### 📦 **Step 3: Finding Maven Version**

```yaml
- name: step 3. finding maven version
  run: mvn --version
```

* Checks the Maven version.
* Usually something like:

  ```
  Apache Maven 3.8.7
  ```

---

#### 🐳 **Step 4: Finding Docker Version**

```yaml
- name: step 4. finding docker version
  run: docker --version
```

* Verifies the Docker CLI is installed and prints its version.
* Typically you'll see:

  ```
  Docker version 24.0.x, build ...
  ```

---

#### ✅ **Step 5: Ending the Job**

```yaml
- name: step 5. Ending the Job
  run: echo "Ended the Job at $(date)"
```

* Logs the job completion time using `date`.

---
