import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import Card from "../components/Card";

export default function Recipients() {
  const [recipients, setRecipients] = useState([]);

  useEffect(() => {
    api.get("/recipients").then(res => setRecipients(res.data)).catch(console.error);
  }, []);

  return (
    <div className="p-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      {recipients.map(r => (
        <Card key={r.id} title={r.firstName + " " + r.lastName}>
          <p>Blood Group Needed: {r.bloodGroup}</p>
          <p>Email: {r.email}</p>
        </Card>
      ))}
    </div>
  );
}
