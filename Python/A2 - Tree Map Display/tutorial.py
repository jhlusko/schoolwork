import pygame

pygame.init()
screen_size = (1024, 666)
screen = pygame.display.set_mode(screen_size)

blue = (0, 0, 255)
sludge = (150, 100, 50) # A lovely brown colour.
white = (255, 255, 255)
black = (0, 0, 0)

# The initial location and size of the sludge rectangle.
x, y = 0, 0
width, height = 100, 150

# The event loop.
running = True
while running:
    event = pygame.event.poll()
    if event.type == pygame.QUIT:
        running = False
    elif event.type == pygame.MOUSEMOTION:
        print "mouse moved to (%d, %d)" % event.pos

    screen.fill(blue)
    pygame.draw.rect(screen, sludge, (x, y, width, height))

    # Get a font and use it render the text to a Surface.
    font = pygame.font.Font(None, 16)
    text_surface = font.render('rectangle location is (%s, %s)' % (x, y),
        1, white)

    # Where to blit the text_surface: the screen height - the font height.
    text_pos = (0, screen_size[1] - font.get_linesize())
    screen.blit(text_surface, text_pos)

    x = x + 1
    y = y + 1
    pygame.display.flip()