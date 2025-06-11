# GitHub Actions 

### GitHub Actions provides a huge number of actions—**thousands**—created and maintained by GitHub and the community. These actions are reusable units of code you can use in workflows. Here's a categorized list of the **most commonly used and official GitHub Actions**, followed by **how to discover all available actions**.

---

## ✅ **Official GitHub Actions**

### 🔹 Repository Management

| Action                      | Description                                                 |
| --------------------------- | ----------------------------------------------------------- |
| `actions/checkout`          | Checks out your repository so the workflow can access it    |
| `actions/cache`             | Caches dependencies and build outputs to speed up workflows |
| `actions/upload-artifact`   | Uploads files from the workflow to view/download later      |
| `actions/download-artifact` | Downloads previously uploaded artifacts                     |

---

### 🔹 Language Runtimes

| Language | Action                   |
| -------- | ------------------------ |
| Java     | `actions/setup-java`     |
| Node.js  | `actions/setup-node`     |
| Python   | `actions/setup-python`   |
| Go       | `actions/setup-go`       |
| .NET     | `actions/setup-dotnet`   |
| Ruby     | `ruby/setup-ruby`        |
| PHP      | `shivammathur/setup-php` |
| Rust     | `actions-rs/toolchain`   |

---

### 🔹 Docker & Containers

| Action                       | Description                             |
| ---------------------------- | --------------------------------------- |
| `docker/setup-buildx-action` | Set up Docker Buildx                    |
| `docker/login-action`        | Log in to DockerHub or another registry |
| `docker/build-push-action`   | Build and push Docker images            |

---

### 🔹 Deployment

| Platform     | Action                                                 |
| ------------ | ------------------------------------------------------ |
| GitHub Pages | `peaceiris/actions-gh-pages` or `actions/deploy-pages` |
| AWS          | `aws-actions/configure-aws-credentials`                |
| Azure        | `azure/login`, `azure/webapps-deploy`                  |
| Google Cloud | `google-github-actions/auth`, `setup-gcloud`           |
| Firebase     | `FirebaseExtended/action-hosting-deploy`               |
| Heroku       | `akhileshns/heroku-deploy`                             |

---

### 🔹 Utility

| Action                                 | Description                                                      |
| -------------------------------------- | ---------------------------------------------------------------- |
| `actions/github-script`                | Run JavaScript using GitHub context (e.g., for issue automation) |
| `actions/setup-node`                   | Install and configure Node.js                                    |
| `peter-evans/create-issue`             | Create a new GitHub issue                                        |
| `peter-evans/close-issue`              | Close GitHub issues automatically                                |
| `stefanzweifel/git-auto-commit-action` | Auto-commit file changes in a workflow                           |

---

## 🔎 How to Discover More Actions

You can explore **ALL available GitHub Actions** from:

👉 [**GitHub Marketplace – Actions**](https://github.com/marketplace?type=actions)

Use filters like:

* Language (Java, Python, etc.)
* Purpose (CI, deployment, security)
* Publisher (GitHub, verified, community)

---

## 🧠 Pro Tip

If you want to find an action for something specific, search GitHub Marketplace like:

* **“Terraform GitHub Action”**
* **“Slack notify GitHub Action”**
* **“Kubernetes deploy GitHub Action”**

---
