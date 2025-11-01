import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppBarHeader from "./components/AppBarHeader";
import Home from "./pages/Home";
import RegisterBloodDonor from "./pages/RegisterBloodDonor";
import SearchBloodDonors from "./pages/SearchBloodDonors";
import RegisterOrganDonor from "./pages/RegisterOrganDonor";
import SearchOrganDonors from "./pages/SearchOrganDonors";
import EmergencyRequest from "./pages/EmergencyRequest";
import AdminDashboard from "./pages/AdminDashboard";

export default function App() {
  return (
    <BrowserRouter>
      <AppBarHeader />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register-blood-donor" element={<RegisterBloodDonor />} />
        <Route path="/search-blood-donors" element={<SearchBloodDonors />} />
        <Route path="/register-organ-donor" element={<RegisterOrganDonor />} />
        <Route path="/search-organ-donors" element={<SearchOrganDonors />} />
        <Route path="/emergency" element={<EmergencyRequest />} />
        <Route path="/admin" element={<AdminDashboard />} />
      </Routes>
    </BrowserRouter>
  );
}
