import React, { useEffect, useState } from "react";
import { Grid, Paper, Typography, Box, LinearProgress, Card, CardContent, Chip } from "@mui/material";
import { BarChart, Bar, PieChart, Pie, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LineChart, Line } from "recharts";
import axios from "../api";

export default function AnalyticsDashboard() {
  const [data, setData] = useState({
    overview: {},
    bloodDistribution: {},
    demandPrediction: {},
    shortages: []
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchAnalytics();
  }, []);

  async function fetchAnalytics() {
    try {
      const [overview, bloodDist, demand, shortages] = await Promise.all([
        axios.get("/api/analytics/overview"),
        axios.get("/api/analytics/blood-distribution"),
        axios.get("/api/analytics/demand-prediction"),
        axios.get("/api/analytics/critical-shortages")
      ]);

      setData({
        overview: overview.data,
        bloodDistribution: bloodDist.data,
        demandPrediction: demand.data,
        shortages: shortages.data
      });
    } catch (err) {
      console.error("Error fetching analytics:", err);
    }
    setLoading(false);
  }

  if (loading) return <LinearProgress />;

  const bloodChartData = Object.entries(data.bloodDistribution || {}).map(([name, value]) => ({
    name,
    value
  }));

  const demandChartData = Object.entries(data.demandPrediction || {}).map(([date, info]) => ({
    date,
    demand: info.predictedDemand,
    riskLevel: info.riskLevel
  }));

  const COLORS = ["#ff6b6b", "#4ecdc4", "#45b7d1", "#f9ca24", "#6c5ce7", "#a29bfe", "#fd79a8", "#fdcb6e"];

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>📊 Analytics Dashboard</Typography>

      {/* Overview Stats */}
      <Grid container spacing={2} sx={{ mb: 3 }}>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="textSecondary">Blood Donors</Typography>
              <Typography variant="h5">{data.overview.totalBloodDonors || 0}</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="textSecondary">Organ Donors</Typography>
              <Typography variant="h5">{data.overview.totalOrganDonors || 0}</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="textSecondary">Emergency Requests</Typography>
              <Typography variant="h5">{data.overview.pendingEmergencies || 0}</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="textSecondary">Responded</Typography>
              <Typography variant="h5">{data.overview.respondedEmergencies || 0}</Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Blood Distribution Chart */}
      <Grid container spacing={2}>
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">Blood Group Distribution</Typography>
            <ResponsiveContainer width="100%" height={300}>
              <PieChart>
                <Pie
                  data={bloodChartData}
                  cx="50%"
                  cy="50%"
                  labelLine={false}
                  label={({ name, value }) => `${name}: ${value}`}
                  outerRadius={80}
                  fill="#8884d8"
                  dataKey="value"
                >
                  {bloodChartData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                  ))}
                </Pie>
                <Tooltip />
              </PieChart>
            </ResponsiveContainer>
          </Paper>
        </Grid>

        {/* Demand Prediction Chart */}
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">7-Day Demand Prediction</Typography>
            <ResponsiveContainer width="100%" height={300}>
              <LineChart data={demandChartData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" />
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="demand" stroke="#8884d8" />
              </LineChart>
            </ResponsiveContainer>
          </Paper>
        </Grid>
      </Grid>

      {/* Critical Shortages */}
      {data.shortages && data.shortages.length > 0 && (
        <Paper sx={{ p: 2, mt: 2 }}>
          <Typography variant="h6" color="error">⚠️ Critical Shortages</Typography>
          {data.shortages.map((shortage, idx) => (
            <Box key={idx} sx={{ display: "flex", gap: 2, mb: 1 }}>
              <Typography>{shortage.bloodGroup}</Typography>
              <Chip
                label={`${shortage.currentCount} units - ${shortage.status}`}
                color={shortage.status === "CRITICAL" ? "error" : "warning"}
              />
            </Box>
          ))}
        </Paper>
      )}
    </Box>
  );
}
