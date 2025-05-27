package br.com.store.domain;

import java.util.List;

public record Box (
        Dimension dimension
) {
    public boolean fit(int h, int w, int c) {
        return permutations(h, w, c)
                .stream()
                .anyMatch(d -> d.fit(dimension));
    }

    private List<Dimension> permutations(int h, int w, int c) {
        return List.of(
                new Dimension(h, w, c),
                new Dimension(h, c, w),
                new Dimension(w, h, c),
                new Dimension(w, c, h),
                new Dimension(c, h, w),
                new Dimension(c, w, h)
        );
    }

    public record Dimension(int height, int width, int length) {
        private boolean fit(Dimension box) {
            return height <= box.height &&
                    width < box.width &&
                    length < box.length;
        }
    }
}
