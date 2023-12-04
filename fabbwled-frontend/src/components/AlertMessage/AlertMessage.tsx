import React from "react";
import { Alert, AlertTitle } from "@mui/material";
import "./alertMessage.scss";

interface AlertMessageProps {
  severity: "error" | "success";
  message: string;
  onClose: () => void;
}

const AlertMessage: React.FC<AlertMessageProps> = ({
  severity,
  message,
  onClose,
}) => {
  return (
    <Alert severity={severity} onClose={onClose} className={severity}>
      <AlertTitle>{severity === "error" ? "Error" : "Success"}</AlertTitle>
      {message}
    </Alert>
  );
};

export default AlertMessage;
