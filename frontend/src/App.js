import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import Donors from "./pages/Donors";
import Recipients from "./pages/Recipients";
import Inventory from "./pages/Inventory";
import Emergency from "./pages/Emergency";
import AdminDashboard from "./pages/AdminDashboard";

function App() {
  return (
    <Router>
      <Navbar />
      <div className="min-h-screen bg-gray-100">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/donors" element={<Donors />} />
          <Route path="/recipients" element={<Recipients />} />
          <Route path="/inventory" element={<Inventory />} />
          <Route path="/emergency" element={<Emergency />} />
          <Route path="/admin" element={<AdminDashboard />} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;
