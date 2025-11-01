import React, { useState } from "react";
import { Box, Button, TextField, MenuItem, Typography, Paper, CircularProgress } from "@mui/material";
import axios from "../api";

export default function SmartMatching() {
  const [form, setForm] = useState({
    type: "blood", // blood or organ
    bloodGroup: "",
    organType: "",
    city: "",
    age: ""
  });
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);

  async function findMatches() {
    setLoading(true);
    try {
      if (form.type === "blood") {
        const resp = await axios.post(`/api/matching/blood-donors?bloodGroup=${form.bloodGroup}&city=${form.city}`);
        setResults(resp.data);
      } else {
        const resp = await axios.post(`/api/matching/organ-donors?organType=${form.organType}&city=${form.city}&bloodGroup=${form.bloodGroup}&age=${form.age}`);
        setResults(resp.data);
      }
    } catch (err) {
      console.error("Error:", err);
    }
    setLoading(false);
  }

  return (
    <Paper elevation={3} sx={{ maxWidth: 600, margin: "42px auto", padding: 3 }}>
      <Typography variant="h5" gutterBottom>🤖 Smart Donor Matching</Typography>

      <TextField
        select
        label="Type"
        value={form.type}
        onChange={e => setForm({ ...form, type: e.target.value })}
        fullWidth
        margin="normal"
      >
        <MenuItem value="blood">Blood Donor</MenuItem>
        <MenuItem value="organ">Organ Donor</MenuItem>
      </TextField>

      {form.type === "blood" ? (
        <>
          <TextField
            label="Blood Group"
            value={form.bloodGroup}
            onChange={e => setForm({ ...form, bloodGroup: e.target.value })}
            fullWidth
            margin="normal"
            placeholder="e.g., O+"
          />
        </>
      ) : (
        <>
          <TextField label="Organ Type" value={form.organType} onChange={e => setForm({ ...form, organType: e.target.value })} fullWidth margin="normal" placeholder="e.g., Kidney" />
          <TextField label="Age" type="number" value={form.age} onChange={e => setForm({ ...form, age: e.target.value })} fullWidth margin="normal" />
          <TextField label="Blood Group" value={form.bloodGroup} onChange={e => setForm({ ...form, bloodGroup: e.target.value })} fullWidth margin="normal" />
        </>
      )}

      <TextField label="City" value={form.city} onChange={e => setForm({ ...form, city: e.target.value })} fullWidth margin="normal" />

      <Button variant="contained" onClick={findMatches} fullWidth sx={{ mt: 2 }} disabled={loading}>
        {loading ? <CircularProgress size={24} /> : "Find Best Matches"}
      </Button>

      {results.length > 0 && (
        <Box sx={{ mt: 3 }}>
          <Typography variant="h6">✅ {results.length} Perfect Matches Found!</Typography>
          {results.map((donor, idx) => (
            <Paper key={donor.id} sx={{ p: 2, mb: 2, backgroundColor: idx === 0 ? "#e8f5e9" : "#f5f5f5" }}>
              <Typography variant="body1"><b>Rank #{idx + 1}</b> {donor.fullName || donor.donorName}</Typography>
              <Typography>{donor.bloodGroup} | {donor.phone}</Typography>
            </Paper>
          ))}
        </Box>
      )}
    </Paper>
  );
}
