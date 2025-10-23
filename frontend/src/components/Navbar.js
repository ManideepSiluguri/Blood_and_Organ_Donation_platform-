import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="bg-red-600 text-white p-4 flex justify-between items-center">
      <h1 className="text-2xl font-bold">Blood & Organ Donation</h1>
      <div className="space-x-4">
        <Link to="/" className="hover:text-gray-200">Home</Link>
        <Link to="/donors" className="hover:text-gray-200">Donors</Link>
        <Link to="/recipients" className="hover:text-gray-200">Recipients</Link>
        <Link to="/inventory" className="hover:text-gray-200">Inventory</Link>
        <Link to="/emergency" className="hover:text-gray-200">Emergency</Link>
        <Link to="/admin" className="hover:text-gray-200">Admin</Link>
      </div>
    </nav>
  );
}
