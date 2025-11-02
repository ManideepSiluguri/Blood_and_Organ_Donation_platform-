# Blood & Organ Donation Platform

A full-stack Java + React platform solving real-time blood and organ donation problems with:
- Smart donor matching using ML
- Advanced analytics dashboard
- Real-time notifications (optional, via WebSocket)

---

## 🚀 What Makes This Different

- **Dual-Platform (Blood + Organ):** Register/search for both blood and organ donors in the same place.
- **Smart Matching:** Advanced AI/ML-based matching (not just search) ranks best donors by compatibility, location, and history.
- **Analytics Dashboard:** Live dashboards with blood group/organ distribution, demand prediction, shortages, donor retention, and more.
- **Real-Time Alerts:** (Optional) Get WebSocket notifications for emergency requests or matches.
- **Modern UI/UX:** Responsive, Material-UI frontend for a seamless experience.

---

## 🟢 Real Problems Solved (Why It’s Useful)

- Emergency blood/organ requests can be resolved in minutes, not hours.
- Hospitals (and patients) discover closest, best-matched donors automatically.
- City-wide shortages and future demand are predicted in advance.
- Tracks and encourages repeat donation, improving community health.

---

## ✨ Features

### Donors & Recipients
- Register as blood/organ donor with full compatibility data
- Search/filter donors by city, type, or AI-rank
- Recipients post emergency requests (instant broadcast possible)
- See and respond to latest urgent needs

### ML Matching & Analytics
- 🤖 ML ranks donor suitability for each request
- 📊 Analytics dashboard: KPIs, pie/line charts, shortages, demand forecasting
- Retention analysis: Track and improve repeat donations

### Admin
- View global and trend stats at a glance
- Spot shortages before they become critical
- Analyze supply/demand, improve outreach

### Optional Real-Time (WebSocket)
- Get instant notification for matches
- In-app alerts for urgent new requests

---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot, Spring Data JPA, H2/MySQL
- **Frontend:** ReactJS, Material-UI (MUI), Recharts (for visualization)
- **AI/ML:** Java scoring algorithms (easy to extend to ML services)
- **Optional Real-Time:** Spring WebSocket/STOMP, sockjs-client, @stomp/stompjs




