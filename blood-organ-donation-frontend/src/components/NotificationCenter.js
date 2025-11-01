import React, { useEffect, useState } from "react";
import { Alert, Box, Snackbar, Badge, Button, Menu, MenuItem, Typography } from "@mui/material";
import { Notifications as NotificationsIcon } from "@mui/icons-material";
import websocketService from "../utils/websocket";

export default function NotificationCenter({ donorId, bloodGroup, city }) {
  const [notifications, setNotifications] = useState([]);
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [currentNotification, setCurrentNotification] = useState(null);
  const [anchorEl, setAnchorEl] = useState(null);

  useEffect(() => {
    websocketService.connect(donorId, bloodGroup, null, city, (message) => {
      const notification = JSON.parse(message.body);
      setNotifications(prev => [notification, ...prev]);
      setCurrentNotification(notification);
      setOpenSnackbar(true);
    });

    return () => websocketService.disconnect();
  }, [donorId, bloodGroup, city]);

  const handleMenuOpen = (e) => setAnchorEl(e.currentTarget);
  const handleMenuClose = () => setAnchorEl(null);

  return (
    <>
      <Button
        onClick={handleMenuOpen}
        startIcon={<Badge badgeContent={notifications.length} color="error"><NotificationsIcon /></Badge>}
        sx={{ color: "white" }}
      >
        Alerts
      </Button>

      <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleMenuClose}>
        {notifications.length > 0 ? (
          notifications.slice(0, 5).map(notif => (
            <MenuItem key={notif.id}>
              <Box>
                <Typography variant="body2" fontWeight="bold">{notif.title}</Typography>
                <Typography variant="caption">{notif.message}</Typography>
              </Box>
            </MenuItem>
          ))
        ) : (
          <MenuItem>No new notifications</MenuItem>
        )}
      </Menu>

      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={() => setOpenSnackbar(false)}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
      >
        <Alert severity={currentNotification?.type === "EMERGENCY" ? "error" : "info"}>
          {currentNotification?.title}: {currentNotification?.message}
        </Alert>
      </Snackbar>
    </>
  );
}
