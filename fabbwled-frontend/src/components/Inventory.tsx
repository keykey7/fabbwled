import { useState } from "react";
import {
  Box,
  Button,
  IconButton,
  Input,
  List,
  ListItemButton,
  ListItemText,
  Modal,
  Typography,
} from "@mui/material";
import { Close } from "@mui/icons-material";
import background from "../../public/inventory-background.png";

interface Item {
  id: number;
  name: string;
}

type InventoryProps = {
  onAction: () => void;
};

const Inventory = ({ onAction }: InventoryProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedItem, setSelectedItem] = useState<Item | null>(null);

  const inventory: Item[] = [
    { id: 1, name: "Item 1" },
    { id: 2, name: "Item 2" },
    { id: 3, name: "Item 3" },
    { id: 4, name: "Item 4" },
    { id: 5, name: "Item 5" },
    { id: 6, name: "Item 6" },
    { id: 7, name: "Item 7" },
  ];

  const filteredItems = inventory.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase()),
  );

  const handleItemClick = (item: Item) => {
    setSelectedItem(item);
  };

  const handleActionClick = () => {
    if (!selectedItem) {
       return;
    }
      alert(`Action performed for ${selectedItem.name}`);
      onAction();
    }
  };

  return (
    <div>
      <button onClick={() => setIsOpen(!isOpen)}>Open Inventory</button>
      <Modal open={isOpen} onClose={() => setIsOpen(false)}>
        <Box
          sx={{
            position: "absolute",
            width: "60vw",
            height: "70vh",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            backgroundImage: `url(${background})`,
            boxShadow: 3,
            borderRadius: 7,
            textAlign: "center",
          }}
        >
          <Box sx={{ justifyContent: "space-between", alignItems: "center" }}>
            <Typography variant="h6">Inventory</Typography>
            <IconButton onClick={() => setIsOpen(false)}>
              <Close />
            </IconButton>
          </Box>
          <Input
            type="text"
            placeholder="Search..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <List
            sx={{
              maxHeight: 200,
              overflowY: "auto",
              alignItems: "center",
              textAlign: "center",
            }}
          >
            {filteredItems.map((item) => (
              <ListItemButton
                key={item.id}
                onClick={() => handleItemClick(item)}
                selected={selectedItem?.id === item.id}
              >
                <ListItemText primary={item.name} />
              </ListItemButton>
            ))}
          </List>
          <Button
            variant="contained"
            color="primary"
            onClick={handleActionClick}
            sx={{ mt: 2 }}
          >
            Action
          </Button>
        </Box>
      </Modal>
    </div>
  );
};

export default Inventory;
