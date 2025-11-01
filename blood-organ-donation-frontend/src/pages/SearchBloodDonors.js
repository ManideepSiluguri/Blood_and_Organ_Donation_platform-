import React, { useState } from "react";
import { TextField, Button, Typography, Paper, Box } from "@mui/material";
import axios from "../api";

export default function SearchBloodDonors() {
  const [bloodGroup, setBloodGroup] = useState("");
  const [results, setResults] = useState([]);

  async function search() {
    if (!bloodGroup) return;
    try {
      const resp = await axios.get("/api/blood-donors/search-by-blood?bloodGroup=" + bloodGroup);
      setResults(resp.data);
    } catch (err) {
      console.error("Error:", err);
    }
  }

  return (
    <Paper elevation={3} sx={{ maxWidth: 500, margin: "42px auto", padding: 3 }}>
      <Typography variant="h5" gutterBottom>Search Blood Donors</Typography>
      <Box sx={{ display: "flex", gap: 1, mb: 2 }}>
        <TextField value={bloodGroup} onChange={e => setBloodGroup(e.target.value)} label="Blood Group (e.g., O+)" sx={{ flex: 1 }} />
        <Button variant="contained" onClick={search}>Search</Button>
      </Box>
      {results.map(d => (
        <Paper key={d.id} sx={{ p: 2, mb: 1 }}>
          <Typography><b>{d.fullName}</b> ({d.bloodGroup})</Typography>
          <Typography color="text.secondary">{d.email} | {d.phone}</Typography>
        </Paper>
      ))}
      {results.length === 0 && bloodGroup && <Typography color="text.secondary">No blood donors found.</Typography>}
    </Paper>
  );
}
