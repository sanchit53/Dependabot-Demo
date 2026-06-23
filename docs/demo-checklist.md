# Live demo readiness checklist

Use this checklist the day before the security-team demo.

## Repository setup

- [ ] Replace placeholder CODEOWNERS teams with real org teams or demo users.
- [ ] Confirm `.github/dependabot.yml` is present on the default branch.
- [ ] Confirm no auto-merge, auto-approve, or merge-on-green workflows exist.
- [ ] Generate npm lockfiles if registry access is available.
- [ ] Push the repository to GitHub and wait for dependency graph ingestion.

## GitHub settings

Enable these under **Settings > Code security and analysis**:

- [ ] Dependency graph.
- [ ] Dependabot alerts.
- [ ] Dependabot security updates.
- [ ] Dependabot version updates.

## Browser tabs to prepare

- [ ] `.github/dependabot.yml`.
- [ ] Dependency graph.
- [ ] Dependabot alerts filtered by severity.
- [ ] One critical/high advisory detail page.
- [ ] One Dependabot security update PR.
- [ ] One Dependabot version update PR.
- [ ] CODEOWNERS file showing manual review routing.

## Talk-track checkpoints

- [ ] Show dependency visibility across app, native, container, and CI/CD ecosystems.
- [ ] Show alert triage by severity and ecosystem.
- [ ] Show a Dependabot PR with advisory context and patched version.
- [ ] Emphasize that humans still review and merge every PR.
- [ ] Call out that vcpkg is included for ecosystem breadth, while npm/pip/Maven provide the most mature security-alert demo path.
