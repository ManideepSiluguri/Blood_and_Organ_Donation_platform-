import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import Card from "../components/Card";

export default function Donors() {
  const [donors, setDonors] = useState([]);

  useEffect(() => {
    api.get("/donors").then(res => setDonors(res.data)).catch(console.error);
  }, []);

  return (
    <div className="p-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      {donors.map(d => (
        <Card key={d.id} title={d.firstName + " " + d.lastName}>
          <p>Blood Group: {d.bloodGroup}</p>
          <p>Age: {d.age}</p>
          <p>Email: {d.email}</p>
        </Card>
      ))}
    </div>
  );
}
