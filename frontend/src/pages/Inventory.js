import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import Card from "../components/Card";

export default function Inventory() {
  const [inventory, setInventory] = useState([]);

  useEffect(() => {
    api.get("/inventory").then(res => setInventory(res.data)).catch(console.error);
  }, []);

  return (
    <div className="p-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      {inventory.map(i => (
        <Card key={i.id} title={i.bloodGroup}>
          <p>Units Available: {i.units}</p>
          <p>Last Updated: {new Date(i.updatedAt).toLocaleDateString()}</p>
        </Card>
      ))}
    </div>
  );
}
