interface RoomService<T extends Room> {
    
    default void clean() {
        System.out.println("Мы почистили вашу комнату");
    }

    void reserve();

    void free();
}
