# One-page live demo script

## Goal

Show the security team how GitHub Dependabot can replace the SCA alerting and remediation workflow currently demonstrated with Veracode SCA.

## Setup before the meeting

1. Push this repo to a GitHub repository.
2. Enable **Dependency graph**, **Dependabot alerts**, and **Dependabot security updates** in **Settings > Code security and analysis**.
3. Wait for dependency graph ingestion and Dependabot alert generation. This often takes a few minutes after the first push.
4. Keep all Dependabot pull requests unmerged so reviewers can inspect them during the demo.


## Presenter prep

Before the meeting, replace placeholder CODEOWNERS teams with real demo owners, generate npm lockfiles if registry access is available, and open `docs/advisory-inventory.md` beside the Dependabot alerts page. This keeps the alert-count discussion grounded if GitHub shows a slightly different number of alerts.

## Talk track

1. **Frame the repo**: “This is not production software. It is a controlled demo with five tiny services in Java, Node.js, Python, C, and TypeScript, plus bonus Docker and GitHub Actions manifests, each pinned to known-vulnerable dependency versions.”
2. **Open the dependency graph**: show that GitHub has identified manifests under `services/reporting-java`, `services/api-node`, `services/worker-python`, `services/native-c`, and `services/cli-typescript`.
3. **Open Dependabot alerts**: filter by severity and package ecosystem. Point out critical/high items first, then the medium/low long tail.
4. **Open a Dependabot PR**: show the advisory link, patched version, manifest diff, release notes when available, and branch protection/review expectations.
5. **Compare operating model**: emphasize native GitHub workflow, reviewable pull requests, auditability, and developer-owned remediation.
6. **Manual control**: explicitly note there is no auto-merge or auto-approval in this repository; security and engineering teams retain human review and merge control.

## Expected result

Expect roughly 30+ alerts spanning critical, high, medium, and low severities. The count may differ slightly during the live demo because GitHub continuously updates advisory mappings and ecosystem support, and some ecosystems expose transitive dependencies differently. If the number is not exact, the demo is still working as long as multiple ecosystems show Dependabot alerts and remediation PRs.

## Close

Ask the team which policies should be standardized for real repositories: alert routing, severity SLAs, branch protection, required reviewers, exception process, and metrics for mean time to remediate.
