import React, { useState } from "react";
import { TextField, Button, Typography, Paper, Box, MenuItem } from "@mui/material";
import axios from "../api";

export default function SearchOrganDonors() {
  const [organType, setOrganType] = useState("");
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const organTypes = ["Kidney", "Liver", "Heart", "Lung", "Pancreas", "Intestine", "Cornea"];

  async function search() {
    if (!organType) {
      alert("Please select an organ type");
      return;
    }
    setLoading(true);
    try {
      const resp = await axios.get(`/api/organ-donors?organType=${organType}`);
      setResults(resp.data);
    } catch (err) {
      console.error("Error searching organ donors:", err);
      alert("Error searching organ donors. Check console for details.");
      setResults([]);
    }
    setLoading(false);
  }

  return (
    <Paper elevation={3} sx={{ maxWidth: 600, margin: "42px auto", padding: 3 }}>
      <Typography variant="h5" gutterBottom>Search Organ Donors</Typography>

      <Box sx={{ display: "flex", gap: 1, mb: 3 }}>
        <TextField
          select
          value={organType}
          onChange={e => setOrganType(e.target.value)}
          label="Select Organ Type"
          sx={{ flex: 1 }}
        >
          <MenuItem value="">-- Select an Organ --</MenuItem>
          {organTypes.map(type => (
            <MenuItem key={type} value={type}>{type}</MenuItem>
          ))}
        </TextField>
        <Button
          variant="contained"
          onClick={search}
          disabled={loading || !organType}
          sx={{ mt: 1 }}
        >
          {loading ? "Searching..." : "Search"}
        </Button>
      </Box>

      {results && results.length > 0 ? (
        <Box>
          <Typography variant="h6" sx={{ mb: 2 }}>Found {results.length} donor(s):</Typography>
          {results.map(o => (
            <Paper key={o.id} sx={{ p: 2, mb: 2, backgroundColor: "#f5f5f5" }}>
              <Typography variant="body1"><b>Name:</b> {o.donorName}</Typography>
              <Typography variant="body1"><b>Organ:</b> {o.organType}</Typography>
              <Typography variant="body1"><b>Blood Group:</b> {o.bloodGroup}</Typography>
              <Typography variant="body1"><b>Phone:</b> {o.phone}</Typography>
              <Typography variant="body1"><b>City:</b> {o.city}</Typography>
              <Typography variant="body1"><b>Status:</b> {o.status}</Typography>
            </Paper>
          ))}
        </Box>
      ) : (
        organType && <Typography color="text.secondary" sx={{ mt: 2 }}>No organ donors found for {organType}.</Typography>
      )}
    </Paper>
  );
}
