import React from "react";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import { Bloodtype } from "@mui/icons-material";
import { Link } from "react-router-dom";
import NotificationCenter from "./NotificationCenter";  // ← ADD THIS IMPORT

export default function AppBarHeader() {
  return (
    <AppBar position="static" color="primary">
      <Toolbar>
        <Bloodtype sx={{ mr: 2 }} />
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          Blood & Organ Donation
        </Typography>

        {/* ← ADD THIS LINE HERE (before other links) */}
        <NotificationCenter
          donorId="donor-123"
          bloodGroup="O+"
          city="Hyderabad"
        />

        <Link to="/" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>Home</Link>
        <Link to="/register-blood-donor" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>Blood Donor</Link>
        <Link to="/search-blood-donors" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>Search Blood</Link>
        <Link to="/register-organ-donor" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>Organ Donor</Link>
        <Link to="/search-organ-donors" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>Search Organ</Link>
        <Link to="/emergency" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>Emergency</Link>
        <Link to="/admin" style={{ color: "#fff", textDecoration: "none" }}>Admin</Link>
        <Link to="/smart-matching" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>AI Matching</Link>
        <Link to="/analytics" style={{ color: "#fff", marginRight: 15, textDecoration: "none" }}>Analytics</Link>

      </Toolbar>
    </AppBar>
  );
}
