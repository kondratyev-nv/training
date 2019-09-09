
#include <memory>

class frequency_queries {
   public:
    frequency_queries();
    ~frequency_queries();

    void add(int x);
    void remove(int x);
    bool has_element_with_frequency(int frequency) const;

   private:
    class impl;
    std::unique_ptr<impl> impl_;
};
