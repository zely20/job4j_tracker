package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    private static final String ADD_ITEM = "INSERT INTO items (name) VALUES (?);";
    private static final String REPLACE_ITEM = "UPDATE items SET name = ? WHERE id = ?;";
    private static final String DELETE_ITEM = "DELETE FROM items WHERE id = ?;";
    private static final String FIND_ALL_ITEM = "SELECT * FROM items";
    private static final String FIND_BY_NAME_ITEM = "SELECT * FROM items WHERE name = ?";
    private static final String FIND_BY_ID_ITEM = "SELECT * FROM items WHERE id = ?";
    private Connection connection;
    private List<Item> result;

    public SqlTracker(){
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
            try (PreparedStatement pr = connection.prepareStatement(ADD_ITEM, Statement.RETURN_GENERATED_KEYS)) {
                pr.setString(1, item.getName());
                pr.executeUpdate();
                try (ResultSet rs = pr.getGeneratedKeys()) {
                    if (rs.next()) {
                        int last_inserted_id = rs.getInt(1);
                        item.setId(Integer.toString(last_inserted_id));
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {

            try (PreparedStatement pr = connection.prepareStatement(REPLACE_ITEM)) {
                pr.setString(1, item.getName());
                pr.setInt(2, Integer.parseInt(id));
                if (pr.executeUpdate() == 0) {
                    return false;
                }
                try (ResultSet rs = pr.getGeneratedKeys()) {
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
            try (PreparedStatement pr = connection.prepareStatement(DELETE_ITEM)) {
                pr.setInt(1, Integer.parseInt(id));
                if (pr.executeUpdate() == 0) {
                    return false;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Item> findAll() {
        result = new LinkedList<>();

            try (PreparedStatement pr = connection.prepareStatement(FIND_ALL_ITEM)) {
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    result.add(new Item(Integer.toString(resultSet.getInt(1)), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        result = new LinkedList<>();
            try (PreparedStatement pr = connection.prepareStatement(FIND_BY_NAME_ITEM)) {
                pr.setString(1, key);
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    result.add(new Item(Integer.toString(resultSet.getInt(1)), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
            try (PreparedStatement pr = connection.prepareStatement(FIND_BY_ID_ITEM)) {
                pr.setInt(1, Integer.parseInt(id));
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    result = new Item(Integer.toString(resultSet.getInt(1)), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
