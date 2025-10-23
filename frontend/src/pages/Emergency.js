import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import Card from "../components/Card";

export default function Emergency() {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    api.get("/emergency").then(res => setRequests(res.data)).catch(console.error);
  }, []);

  return (
    <div className="p-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      {requests.map(r => (
        <Card key={r.id} title={`Emergency: ${r.bloodGroup}`}>
          <p>Status: {r.status}</p>
          <p>Requested On: {new Date(r.createdAt).toLocaleString()}</p>
        </Card>
      ))}
    </div>
  );
}
