import React, { useState } from "react";
import {
  Box, Button, TextField, Snackbar, Alert, Typography, Paper
} from "@mui/material";
import axios from "../api";

export default function EmergencyRequest() {
  const [form, setForm] = useState({
    description: "", location: "", contactPhone: "", bloodGroupNeeded: ""
  });
  const [open, setOpen] = useState(false);
  const [feedback, setFeedback] = useState({ type: "success", message: "" });

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      await axios.post("/api/emergencies", form);
      setFeedback({ type: "success", message: "Emergency request sent! Help is on the way." });
      setForm({ description:"",location:"",contactPhone:"",bloodGroupNeeded:"" });
    } catch (err) {
      setFeedback({ type: "error", message: "Error sending request. Try again!" });
    }
    setOpen(true);
  }

  return (
    <Paper elevation={3} sx={{ maxWidth: 480, margin: "42px auto", padding: 3 }}>
      <Typography variant="h5" gutterBottom>Emergency Request</Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate>
        <TextField name="description" label="Describe your emergency" fullWidth margin="normal" required onChange={handleChange} value={form.description} multiline minRows={2} />
        <TextField name="location" label="Location" fullWidth margin="normal" required onChange={handleChange} value={form.location} />
        <TextField name="contactPhone" label="Contact Phone" fullWidth margin="normal" required onChange={handleChange} value={form.contactPhone} />
        <TextField name="bloodGroupNeeded" label="Blood Group Needed" fullWidth margin="normal" required onChange={handleChange} value={form.bloodGroupNeeded} />
        <Button type="submit" variant="contained" color="secondary" fullWidth sx={{ mt: 2 }}>Send Request</Button>
      </Box>
      <Snackbar
        open={open}
        autoHideDuration={5000}
        onClose={() => setOpen(false)}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert onClose={() => setOpen(false)} severity={feedback.type}>{feedback.message}</Alert>
      </Snackbar>
    </Paper>
  );
}
