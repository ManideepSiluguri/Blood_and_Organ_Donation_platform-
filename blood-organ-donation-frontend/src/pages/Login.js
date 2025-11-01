import React, { useState } from "react";
import { Paper, Typography, TextField, Button, Snackbar, Alert, Box } from "@mui/material";

export default function Login() {
  const [form, setForm] = useState({ username: "", password: "" });
  const [open, setOpen] = useState(false);

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  function handleSubmit(e) {
    e.preventDefault();
    // TODO: Implement actual login logic
    setOpen(true);
  }

  return (
    <Paper elevation={3} sx={{ maxWidth: 400, margin: "42px auto", padding: 3 }}>
      <Typography variant="h5" gutterBottom>Admin Login</Typography>
      <Box component="form" onSubmit={handleSubmit}>
        <TextField name="username" label="Username" fullWidth margin="normal" onChange={handleChange} value={form.username} required />
        <TextField name="password" label="Password" type="password" fullWidth margin="normal" onChange={handleChange} value={form.password} required />
        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>Login</Button>
      </Box>
      <Snackbar open={open} autoHideDuration={4000} onClose={() => setOpen(false)} anchorOrigin={{ vertical: "bottom", horizontal: "center" }}>
        <Alert onClose={() => setOpen(false)} severity="info">Login feature coming soon!</Alert>
      </Snackbar>
    </Paper>
  );
}
