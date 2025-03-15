import header;
using namespace std;

struct CircleGenerator {
	static float constexpr pi{ 3.1415926535897932f };
	float const radius = 0.0f;
	uint32_t const quality = 0;
	float const da = 0.0f;

	CircleGenerator(float radius_, uint32_t quality_)
		: radius{ radius_ }
		, quality{ quality_ }
		, da{ (2.0f * pi) / static_cast<float>(quality) } {}

	sf::Vector2f getPoint(uint32_t i) const {
		float const angle{ da * static_cast<float>(i) };
		return { radius * sf::Vector2f{cos(angle),sin(angle)} };
	}
};

void generateCircle(sf::VertexArray& vertex_array, sf::Vector2f position, float radius, uint32_t quality) {
	CircleGenerator const generator{ radius,quality };
	for (uint32_t i{ 0 }; i < quality; ++i) {
		vertex_array[i].position = position + generator.getPoint(i);
	}
}

struct RoundedRectangleGenerator {
	sf::Vector2f const size;
	sf::Vector2f const centers[4];
	uint32_t const arc_quality;
	CircleGenerator const generator;

	RoundedRectangleGenerator(sf::Vector2f size_, float radius, uint32_t quality)
		: size{ size_ }
		, centers{
			{size.x - radius,size.y - radius},
			{radius,size.y - radius},
			{radius,radius},
			{size.x - radius,radius}
		}
		, arc_quality{ quality / 4 }
		, generator{ radius,quality - 4 } {}

	sf::Vector2f getPoint(uint32_t i) const {
		uint32_t const corner_idx{ i / arc_quality };
		return { centers[corner_idx] + generator.getPoint(i - corner_idx) };
	}
};

void generateRoundedRectangle(sf::VertexArray& vertex_array, sf::Vector2f position, sf::Vector2f size, float radius, uint32_t quality) {
	RoundedRectangleGenerator const generator{ size,radius,quality };
	for (uint32_t i{ 0 }; i < quality; ++i) {
		vertex_array[i].position = position + generator.getPoint(i);
	}
}

void generateOutline(sf::VertexArray& vertex_array, sf::Vector2f position, sf::Vector2f size, float radius, float thickness, uint32_t quality) {
	RoundedRectangleGenerator const generator_out{ size,radius,quality };
	sf::Vector2f const in_offset{ thickness,thickness };
	sf::Vector2f const in_size = size - 2.0f * in_offset;
	RoundedRectangleGenerator const generator_in{ in_size,radius - thickness,quality };
	for (uint32_t i{ 0 }; i < quality; ++i) {
		vertex_array[2 * i + 0].position = position + in_offset + generator_in.getPoint(i);
		vertex_array[2 * i + 1].position = position + generator_out.getPoint(i);
	}
	vertex_array[2 * quality + 0].position = position + in_offset + generator_in.getPoint(0);
	vertex_array[2 * quality + 1].position = position + generator_out.getPoint(0);
}

int main() {
	sf::RenderWindow window{ sf::VideoMode({800,600}),"My window" };
	uint32_t quality = 256;
	sf::VertexArray vertex_array{ sf::PrimitiveType::TriangleFan,quality };
	sf::Vector2f position{ 100,100 };
	sf::Vector2f size{ 500,300 };
	float radius = 50.0f;
	generateRoundedRectangle(vertex_array, position, size, radius, quality);
	while (window.isOpen()) {
		while (const std::optional event = window.pollEvent()) {
			if (event->is<sf::Event::Closed>()) {
				window.close();
			}
		}
		window.clear(sf::Color::Black);
		window.draw(vertex_array);
		window.display();
	}
}